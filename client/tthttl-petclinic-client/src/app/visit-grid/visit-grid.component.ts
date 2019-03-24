import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {VisitRow, VisitWithPet} from './visit.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-visit-grid',
  templateUrl: './visit-grid.component.html',
  styleUrls: ['./visit-grid.component.scss']
})
export class VisitGridComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private router: Router) { }

  private gridApi;
  private selectedRow: VisitRow;

  columnDefs = [
    {
      headerName: 'Visit details', children: [
        { headerName: 'Date', field: 'date', sortable: true, filter: true },
        { headerName: 'Description', field: 'description', sortable: true, filter: true }
      ]
    },
    {
      headerName: 'Customer details', children: [
        { headerName: 'Pet', field: 'pet', sortable: true, filter: true },
        { headerName: 'Owner', field: 'owner', sortable: true, filter: true },
      ]
    }
  ];

  rowData: any;
  visitRows: VisitRow[] = [];

  onGridReady(params) {
    this.gridApi = params.api;
  }

  ngOnInit() {
    this.httpClient.get('http://localhost:8080/visits/with-pet').subscribe((visits: VisitWithPet[]) => {
      visits.forEach(visit => {
        const visitRow: VisitRow = {
          id: visit.id,
          date: visit.date,
          description: visit.description,
          pet: visit.pet.name,
          owner: visit.pet.owner
        };
        this.visitRows.push(visitRow);
      });
      this.rowData = this.visitRows;
    });
  }

  getSelectedRowData() {
    const selected: VisitRow = this.gridApi.getSelectedRows().pop();
    this.router.navigate(['/form/visits/'.concat(selected.id)]);
  }

}
