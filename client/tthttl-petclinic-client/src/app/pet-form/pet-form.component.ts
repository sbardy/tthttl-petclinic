import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Owner, Pet, PetType} from '../customer-grid/customer.model';
import {ActivatedRoute, Router} from '@angular/router';
import {RestClient} from '../services/rest-client.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-pet-form',
  templateUrl: './pet-form.component.html',
  styleUrls: ['./pet-form.component.scss']
})
export class PetFormComponent implements OnInit, OnDestroy {

  constructor(
    private restClient: RestClient,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  petTypes: PetType[] = [];
  owners: Owner[] = [];

  isUpdate: boolean;
  petId: string;
  currentOwnerId: string;
  subscriptions: Subscription[] = [];

  petFormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    birthDate: new FormControl('', [Validators.required, Validators.pattern('[\\d]{4}-[\\d]{2}-[\\d]{2}')]),
    petType: new FormControl('Please select', Validators.required),
    owner: new FormControl('Please select', Validators.required)
  });

  ngOnInit() {
    this.subscriptions.push(this.restClient.getOwners().subscribe((owners: Owner[]) => {
      this.owners = owners;
      this.subscriptions.push(this.restClient.getPetTypes().subscribe((petTypes: PetType[]) => {
        this.petTypes = petTypes;
        this.subscriptions.push(this.activatedRoute.params.subscribe(params => {
          this.petId = params.id;
          if (this.petId) {
            this.subscriptions.push(this.restClient.getPet(this.petId).subscribe((pet: Pet) => {
              this.isUpdate = true;
              this.petFormGroup.controls.name.setValue(pet.name);
              this.petFormGroup.controls.birthDate.setValue(pet.birthDate);
              this.petFormGroup.controls.petType.setValue(this.petTypes.find(type => type.id === pet.type.id));
              this.petFormGroup.controls.owner.setValue(this.matchOwnerWithPet());
            }));
          }
        }));
      }));
    }));
  }

  matchOwnerWithPet(): Owner {
    let matchingOwner: Owner;
    this.owners.forEach(owner => {
      const foundPet = owner.pets.find(pet => pet.id.toString() === this.petId);
      if (foundPet) {
        matchingOwner = owner;
        this.currentOwnerId = owner.id;
      }
    });
    return matchingOwner;
  }

  onSubmit() {
    const newOwnerId = this.petFormGroup.controls.owner.value.id;
    const petToSave: Pet = {
      id: '',
      name: this.petFormGroup.controls.name.value,
      birthDate: this.petFormGroup.controls.birthDate.value,
      type: this.petFormGroup.controls.petType.value,
      owner: this.petFormGroup.controls.owner.value
    };
    if (this.isUpdate) {
      if (newOwnerId === this.currentOwnerId) {
        this.subscriptions.push(this.restClient.updatePet(this.petId, petToSave)
          .subscribe(data => this.router.navigate(['/customers']),
            error => console.log(error)));
      } else {
        this.subscriptions.push(this.restClient.deletePet(this.petId).subscribe(() => {
          this.restClient.savePet(newOwnerId, petToSave)
            .subscribe(data => this.router.navigate(['/customers']),
              error => console.log(error));
        }, error => console.log(error)));
      }
    } else {
      this.subscriptions.push(this.restClient.savePet(newOwnerId, petToSave)
        .subscribe(data => this.router.navigate(['/customers']),
          error => console.log(error)));
    }
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}
