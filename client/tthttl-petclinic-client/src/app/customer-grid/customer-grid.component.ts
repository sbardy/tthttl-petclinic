import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-customer-grid',
  templateUrl: './customer-grid.component.html',
  styleUrls: ['./customer-grid.component.sass']
})
export class CustomerGridComponent implements OnInit {

  constructor(private httpClient: HttpClient) { }

  columnDefs = [
    { headerName: 'Id', field: 'id', sortable: true, filter: true },
    { headerName: 'First Name', field: 'firstName', sortable: true, filter: true },
    { headerName: 'Last Name', field: 'lastName', sortable: true, filter: true },
    { headerName: 'Address', field: 'address', sortable: true, filter: true },
    { headerName: 'City', field: 'city', sortable: true, filter: true },
    { headerName: 'Telephone', field: 'telephone', sortable: true, filter: true }
  ];

  rowData: any;

  ngOnInit() {
    this.rowData = this.httpClient.get('http://localhost:8080/owners');
  }

}
