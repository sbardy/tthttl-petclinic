import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Owner} from '../customer-grid/customer.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-owner-form',
  templateUrl: './owner-form.component.html',
  styleUrls: ['./owner-form.component.scss']
})
export class OwnerFormComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private router: Router) { }

  ownerFormGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    telephone: new FormControl('', [Validators.required, Validators.pattern('[0-9]{9}')])
  });

  ngOnInit() {
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
    this.httpClient.post('http://localhost:8080/owners', ownerToSave)
      .subscribe(data => {
        console.log(data);
        this.router.navigate(['/customers']);
      }, error => console.log(error));
  }

}
