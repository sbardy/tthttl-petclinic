import {Component, OnDestroy, OnInit} from '@angular/core';
import {Vet, VetRow} from './vet-model';
import {Router} from '@angular/router';
import {RestClient} from '../services/rest-client.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-vet-grid',
  templateUrl: './vet-grid.component.html',
  styleUrls: ['./vet-grid.component.scss']
})
export class VetGridComponent implements OnInit, OnDestroy {

  private gridApi;

  columnDefs = [
    { headerName: 'First Name', field: 'firstName', sortable: true, filter: true, checkboxSelection: true },
    { headerName: 'Last Name', field: 'lastName', sortable: true, filter: true },
    { headerName: 'Specialties', field: 'specialties', sortable: true, filter: true }
  ];

  private rowData: any;
  private vetRows: VetRow[] = [];
  private selected: VetRow;
  private subscriptions: Subscription[] = [];

  constructor(
    private router: Router,
    private restClient: RestClient) { }

  onGridReady(params) {
    this.gridApi = params.api;
  }

  ngOnInit() {
    this.subscriptions.push(this.restClient.getVets().subscribe((data: Vet[]) => {
      data.forEach(vet => this.vetRows.push(this.restClient.mapToVetRow(vet)));
      this.rowData = this.vetRows;
    }));
  }

  private setSelectedRow() {
    this.selected = this.gridApi.getSelectedRows().pop();
  }

  private updateSelected() {
    this.router.navigate(['/form/vets/'.concat(this.selected.id)]);
  }

  private deleteSelected() {
    this.subscriptions.push(this.restClient.deleteVet(this.selected.id)
      .subscribe(() => this.gridApi.updateRowData({ remove: [this.selected] })));
  }

  private isSelected() {
    return this.gridApi && this.selected;
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}
