import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Owner, OwnerRow, Pet, PetRow} from './customer.model';

@Component({
  selector: 'app-customer-grid',
  templateUrl: './customer-grid.component.html',
  styleUrls: ['./customer-grid.component.scss']
})
export class CustomerGridComponent implements OnInit {

  constructor(private httpClient: HttpClient) { }

  columnDefsOwners = [
    { headerName: 'First Name', field: 'firstName', sortable: true, filter: true },
    { headerName: 'Last Name', field: 'lastName', sortable: true, filter: true },
    { headerName: 'Address', field: 'address', sortable: true, filter: true },
    { headerName: 'City', field: 'city', sortable: true, filter: true },
    { headerName: 'Telephone', field: 'telephone', sortable: true, filter: true },
    { headerName: 'Pets', field: 'pets', sortable: true, filter: true }
  ];

  rowDataOwners: any;
  ownerRows: OwnerRow[] = [];

  columnDefsPets = [
    { headerName: 'Name', field: 'name', sortable: true, filter: true },
    { headerName: 'Birth Date', field: 'birthdate', sortable: true, filter: true },
    { headerName: 'Pet Type', field: 'type', sortable: true, filter: true },
    { headerName: 'Owner', field: 'owner', sortable: true, filter: true },
  ];

  rowDataPets: any;
  petRows: PetRow[] = [];

  ngOnInit() {
    this.httpClient.get('http://localhost:8080/owners').subscribe((owners: Owner[]) => {
      owners.map(owner => {
        const ownerRow: OwnerRow = {
          id: owner.id,
          firstName: owner.firstName,
          lastName: owner.lastName,
          address: owner.address,
          city: owner.city,
          telephone: owner.telephone,
          pets: this.concatPets(owner.pets)
        };
        this.ownerRows.push(ownerRow);
      });
      this.rowDataOwners = this.ownerRows;
    });
    this.httpClient.get('http://localhost:8080/pets').subscribe((pets: Pet[]) => {
      pets.map(pet => {
        const petRow: PetRow = {
          id: pet.id,
          name: pet.name,
          birthdate: pet.birthDate,
          type: pet.type ? pet.type.name : '',
          owner: pet.owner
        };
        this.petRows.push(petRow);
      });
      this.rowDataPets = this.petRows;
    });
  }

  private concatPets(pets: Pet[]): string {
    let petNames = '';
    pets.forEach((pet, index) => {
      if (index > 0) {
        petNames = petNames.concat(', ' + pet.name);
      } else {
        petNames = petNames.concat(pet.name);
      }
    });
    return petNames;
  }

}
