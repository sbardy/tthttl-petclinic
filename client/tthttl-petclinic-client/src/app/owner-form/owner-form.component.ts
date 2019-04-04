import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Owner} from '../customer-grid/customer.model';
import {ActivatedRoute, Router} from '@angular/router';
import {RestClient} from '../services/rest-client.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-owner-form',
  templateUrl: './owner-form.component.html',
  styleUrls: ['./owner-form.component.scss']
})
export class OwnerFormComponent implements OnInit, OnDestroy {

  constructor(
    private restClient: RestClient,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ownerFormGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    telephone: new FormControl('', [Validators.required, Validators.pattern('[0-9]{9}')])
  });

  isUpdate: boolean;
  private ownerId: string;
  private subscriptions: Subscription[] = [];

  ngOnInit() {
    this.subscriptions.push(this.activatedRoute.params.subscribe(params => {
      this.ownerId = params.id;
      if (this.ownerId) {
        this.restClient.getOwner(this.ownerId).subscribe((owner: Owner) => {
          this.isUpdate = true;
          this.ownerFormGroup.controls.firstName.setValue(owner.firstName);
          this.ownerFormGroup.controls.lastName.setValue(owner.lastName);
          this.ownerFormGroup.controls.address.setValue(owner.address);
          this.ownerFormGroup.controls.city.setValue(owner.city);
          this.ownerFormGroup.controls.telephone.setValue(owner.telephone);
        });
      }
    }));
  }

  onSubmit() {
    const ownerToSave: Owner = {
      id: '',
      firstName: this.ownerFormGroup.controls.firstName.value,
      lastName: this.ownerFormGroup.controls.lastName.value,
      address: this.ownerFormGroup.controls.address.value,
      city: this.ownerFormGroup.controls.city.value,
      telephone: this.ownerFormGroup.controls.telephone.value,
      pets: []
    };
    if (this.isUpdate) {
      this.subscriptions.push(this.restClient.updateOwner(this.ownerId, ownerToSave)
        .subscribe(data => {
          this.router.navigate(['/customers']);
        }, error => console.log(error)));
    } else {
      this.subscriptions.push(this.restClient.saveOwner(ownerToSave)
        .subscribe(data => {
          this.router.navigate(['/customers']);
        }, error => console.log(error)));
    }
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}
