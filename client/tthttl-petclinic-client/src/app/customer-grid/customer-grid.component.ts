import {Component, OnDestroy, OnInit} from '@angular/core';
import {Owner, OwnerRow, Pet, PetRow} from './customer.model';
import {Router} from '@angular/router';
import {RestClient} from '../services/rest-client.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-customer-grid',
  templateUrl: './customer-grid.component.html',
  styleUrls: ['./customer-grid.component.scss']
})
export class CustomerGridComponent implements OnInit, OnDestroy {

  constructor(
    private router: Router,
    private restClient: RestClient) { }

  columnDefsOwners = [
    { headerName: 'First Name', field: 'firstName', sortable: true, filter: true, checkboxSelection: true },
    { headerName: 'Last Name', field: 'lastName', sortable: true, filter: true },
    { headerName: 'Address', field: 'address', sortable: true, filter: true },
    { headerName: 'City', field: 'city', sortable: true, filter: true },
    { headerName: 'Telephone', field: 'telephone', sortable: true, filter: true },
    { headerName: 'Pets', field: 'pets', sortable: true, filter: true }
  ];

  private ownerApi;
  private rowDataOwners: any;
  private ownerRows: OwnerRow[] = [];
  private selectedOwnerRow: OwnerRow;

  columnDefsPets = [
    { headerName: 'Name', field: 'name', sortable: true, filter: true, checkboxSelection: true },
    { headerName: 'Birth Date', field: 'birthdate', sortable: true, filter: true },
    { headerName: 'Pet Type', field: 'type', sortable: true, filter: true },
    { headerName: 'Owner', field: 'owner', sortable: true, filter: true },
  ];

  private petApi;
  private rowDataPets: any;
  private petRows: PetRow[] = [];
  private selectedPetRow: PetRow;
  private subscriptions: Subscription[] = [];

  onOwnerGridReady(params) {
    this.ownerApi = params.api;
  }

  onPetGridReady(params) {
    this.petApi = params.api;
  }

  ngOnInit() {
    this.initRowDataOwners();
    this.initRowDataPets();
  }

  private initRowDataOwners() {
    this.subscriptions.push(this.restClient.getOwners().subscribe((owners: Owner[]) => {
      owners.forEach(owner => this.ownerRows.push(this.restClient.mapOwnerToRow(owner)));
      this.rowDataOwners = this.ownerRows;
    }));
  }

  private initRowDataPets() {
    this.subscriptions.push(this.restClient.getPets().subscribe((pets: Pet[]) => {
      pets.forEach(pet => this.petRows.push(this.restClient.mapPetToPetRow(pet)));
      this.rowDataPets = this.petRows;
    }));
  }

  private deleteSelectedOwner() {
    this.subscriptions.push(this.restClient.deleteOwner(this.selectedOwnerRow.id).subscribe(() => {
      this.ownerApi.updateRowData({ remove: [this.selectedOwnerRow] });
      this.subscriptions.push(this.restClient.getPets().subscribe((pets: Pet[]) => {
        const updatedRows: PetRow[] = pets.map(pet => this.restClient.mapPetToPetRow(pet));
        this.petApi.setRowData(updatedRows);
      }));
    }));
  }

  private deleteSelectedPet() {
    this.subscriptions.push(this.restClient.deletePet(this.selectedPetRow.id).subscribe(() => {
      this.petApi.updateRowData({ remove: [this.selectedPetRow] });
      this.subscriptions.push(this.restClient.getOwners().subscribe((owners: Owner[]) => {
        const updatedRows: OwnerRow[] = owners.map(owner => this.restClient.mapOwnerToRow(owner));
        this.ownerApi.setRowData(updatedRows);
      }));
    }));
  }

  private setSelectedOwnerRow() {
    this.selectedOwnerRow = this.ownerApi.getSelectedRows().pop();
  }

  private updateSelectedOwner() {
    this.router.navigate(['/form/owners/'.concat(this.selectedOwnerRow.id)]);
  }

  private isOwnerSelected() {
    return this.ownerApi && this.selectedOwnerRow;
  }

  private isPetSelected() {
    return this.petApi && this.selectedPetRow;
  }

  private setSelectedPetRow() {
    this.selectedPetRow = this.petApi.getSelectedRows().pop();
  }

  private updateSelectedPet() {
    this.router.navigate(['/form/pets/'.concat(this.selectedPetRow.id)]);
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}
