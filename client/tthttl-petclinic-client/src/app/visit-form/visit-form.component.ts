import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Pet} from '../customer-grid/customer.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Visit, VisitRequestBody} from '../visit-grid/visit.model';
import {RestClient} from '../services/rest-client.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-visit-form',
  templateUrl: './visit-form.component.html',
  styleUrls: ['./visit-form.component.scss']
})
export class VisitFormComponent implements OnInit, OnDestroy {

  constructor(
    private restClient: RestClient,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

   pets: Pet[] = [];
   isUpdate: boolean;
   visitId: string;
   subscriptions: Subscription[] = [];

  visitFormGroup = new FormGroup({
    date: new FormControl('', Validators.required),
    description: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    pet: new FormControl('Please select', Validators.required)
  });

  ngOnInit() {
    this.subscriptions.push(this.restClient.getPets().subscribe((pets: Pet[]) => {
      this.pets = pets;
      this.subscriptions.push(this.activatedRoute.params.subscribe(params => {
        this.visitId = params.id;
        if (this.visitId) {
          this.subscriptions.push(this.restClient.getVisit(this.visitId).subscribe((visit: Visit) => {
            this.isUpdate = true;
            this.visitFormGroup.controls.date.setValue(visit.date);
            this.visitFormGroup.controls.description.setValue(visit.description);
            this.visitFormGroup.controls.pet.patchValue(this.pets.find(pet => pet.id === visit.petId));
          }));
        }
      }));
    }));
  }

  onSubmit() {
    const visitToSave: VisitRequestBody = {
      date: this.visitFormGroup.controls.date.value,
      description: this.visitFormGroup.controls.description.value,
      petId: this.visitFormGroup.controls.pet.value.id
    };
    if (this.isUpdate) {
      this.subscriptions.push(this.restClient.updateVisit(this.visitId, visitToSave)
        .subscribe(data => {
          console.log(data);
          this.router.navigate(['/visits']);
        },
          error => console.log(error)));
    } else {
      this.subscriptions.push(this.restClient.saveVisit(visitToSave)
        .subscribe(data => {
          console.log(data);
          this.router.navigate(['/visits']);
        },
          error => console.log(error)));
    }
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}
