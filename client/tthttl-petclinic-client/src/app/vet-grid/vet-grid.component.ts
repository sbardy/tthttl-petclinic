import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Specialty, Vet, VetRow} from './vet-model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-vet-grid',
  templateUrl: './vet-grid.component.html',
  styleUrls: ['./vet-grid.component.scss']
})
export class VetGridComponent implements OnInit {

  private gridApi;

  columnDefs = [
    { headerName: 'First Name', field: 'firstName', sortable: true, filter: true },
    { headerName: 'Last Name', field: 'lastName', sortable: true, filter: true },
    { headerName: 'Specialties', field: 'specialties', sortable: true, filter: true }
  ];

  rowData: any;
  vetRows: VetRow[] = [];

  constructor(
    private httpClient: HttpClient,
    private router: Router) { }

  onGridReady(params) {
    this.gridApi = params.api;
  }

  ngOnInit() {
    this.httpClient.get('http://localhost:8080/vets').subscribe((data: Vet[]) => {
      data.map(vet => {
        const row: VetRow = {
          id: vet.id,
          firstName: vet.firstName,
          lastName: vet.lastName,
          specialties: this.appendSpecialties(vet.specialties)
        };
        this.vetRows.push(row);
      });
      this.rowData = this.vetRows;
    });
  }

  private appendSpecialties(specialties: Specialty[]) {
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

  getSelectedRowData() {
    const selected: VetRow = this.gridApi.getSelectedRows().pop();
    this.router.navigate(['/form/vets/'.concat(selected.id)]);
  }

}
