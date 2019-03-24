import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Specialty, Vet} from '../vet-grid/vet-model';

@Component({
  selector: 'app-vet-form',
  templateUrl: './vet-form.component.html',
  styleUrls: ['./vet-form.component.scss']
})
export class VetFormComponent implements OnInit {

  specialties: Specialty[] = [];
  isUpdate: boolean;
  vetId: string;

  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  vetFormGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    specialties: new FormControl('', Validators.required)
  });

  ngOnInit() {
    this.httpClient.get('http://localhost:8080/vets/specialties')
      .subscribe((specialties: Specialty[]) => {
        this.specialties = specialties;
        this.activatedRoute.params.subscribe(params => {
          this.vetId = params.id;
          if (this.vetId) {
            this.httpClient.get('http://localhost:8080/vets/'.concat(this.vetId)).subscribe((vet: Vet) => {
              this.isUpdate = true;
              this.vetFormGroup.controls.firstName.setValue(vet.firstName);
              this.vetFormGroup.controls.lastName.setValue(vet.lastName);
              this.vetFormGroup.controls.specialties.setValue(this.specialtyArrayToString(vet.specialties));
            });
          }
        });
      });
  }

  onSubmit() {
    const vetToSave: Vet = {
      id: '',
      firstName: this.vetFormGroup.controls.firstName.value,
      lastName: this.vetFormGroup.controls.lastName.value,
      specialties: this.parseSpecialties(this.vetFormGroup.controls.specialties.value)
    };
    if (this.isUpdate) {
      this.httpClient.put('http://localhost:8080/vets/'.concat(this.vetId), vetToSave)
        .subscribe(data => {
          console.log(data);
          this.router.navigate(['/vets']);
        }, error => console.log(error));
    } else {
      this.httpClient.post('http://localhost:8080/vets', vetToSave)
        .subscribe(data => {
          console.log(data);
          this.router.navigate(['/vets']);
        }, error => console.log(error));
    }
  }

  private specialtyArrayToString(specialties: Specialty[]): string {
    let result = '';
    specialties.forEach((specialty, index) => {
      if (index > 0) {
        result = result.concat(', ' + specialty.name);
      } else {
        result = result.concat(specialty.name);
      }
    });
    return result;
  }

  private parseSpecialties(controlValue: string): Specialty[] {
    const inputStrings: string[] = controlValue.split(',');
    const result: Specialty[] = [];
    inputStrings
      .map(inputString => {
        inputString = inputString.trim();
        inputString = inputString.toLowerCase();
        return inputString;
      })
      .map(inputString => {
        const matchingSpecialty: Specialty = this.specialties.find(specialty => specialty.name === inputString);
        if (matchingSpecialty) {
          result.push(matchingSpecialty);
        } else {
          const newValue: Specialty = {
            id: '',
            name: inputString
          };
          result.push(newValue);
        }
      });
    return result;
  }

}
