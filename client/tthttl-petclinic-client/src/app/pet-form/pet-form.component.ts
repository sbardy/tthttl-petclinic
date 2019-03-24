import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Owner, Pet, PetType} from '../customer-grid/customer.model';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-pet-form',
  templateUrl: './pet-form.component.html',
  styleUrls: ['./pet-form.component.scss']
})
export class PetFormComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private router: Router
  ) { }

  petTypes: PetType[] = [];
  owners: Owner[] = [];

  petFormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    birthDate: new FormControl('', [Validators.required, Validators.pattern('[\\d]{4}-[\\d]{2}-[\\d]{2}')]),
    petType: new FormControl('', Validators.required),
    owner: new FormControl('', Validators.required)
  });

  ngOnInit() {
    this.httpClient.get('http://localhost:8080/owners').subscribe((owners: Owner[]) => this.owners = owners);
    this.httpClient.get('http://localhost:8080/pets/pet-types').subscribe((petTypes: PetType[]) => this.petTypes = petTypes);
  }

  private createURI(ownerId: string) {
    return 'http://localhost:8080/pets/owner/'.concat(ownerId);
  }

  onSubmit() {
    const petToSave: Pet = {
      id: '',
      name: this.petFormGroup.controls.name.value,
      birthDate: this.petFormGroup.controls.birthDate.value,
      type: this.petFormGroup.controls.petType.value,
      owner: this.petFormGroup.controls.owner.value
    };
    this.httpClient.post(this.createURI(this.petFormGroup.controls.owner.value.id), petToSave)
      .subscribe(data => {
        console.log(data);
        this.router.navigate(['/customers']);
      },
        error => console.log(error));
  }

}
