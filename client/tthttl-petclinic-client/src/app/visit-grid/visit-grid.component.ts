import {Component, OnDestroy, OnInit} from '@angular/core';
import {VisitRow, VisitWithPet} from './visit.model';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {RestClient} from '../services/rest-client.service';

@Component({
  selector: 'app-visit-grid',
  templateUrl: './visit-grid.component.html',
  styleUrls: ['./visit-grid.component.scss']
})
export class VisitGridComponent implements OnInit, OnDestroy {

  constructor(
    private restClient: RestClient,
    private router: Router) { }

   gridApi;
   selectedRow: VisitRow;
   subscriptions: Subscription[] = [];

  columnDefs = [
    {
      headerName: 'Visit details', children: [
        { headerName: 'Date', field: 'date', sortable: true, filter: true, checkboxSelection: true },
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
    this.subscriptions.push(this.restClient.getVisitWithPets().subscribe((visits: VisitWithPet[]) => {
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
    }));
  }

  setSelectedRow() {
    this.selectedRow = this.gridApi.getSelectedRows().pop();
  }

  isSelected() {
    return this.gridApi && this.selectedRow;
  }

  updateSelected() {
    this.router.navigate(['/form/visits/'.concat(this.selectedRow.id)]);
  }

  deleteSelected() {
    this.subscriptions.push(this.restClient.deleteVisit(this.selectedRow.id)
      .subscribe(() => {
        this.gridApi.updateRowData({ remove: [this.selectedRow] });
      }, error => console.log(error)));
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}
