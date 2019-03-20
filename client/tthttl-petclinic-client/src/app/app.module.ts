import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {WelcomeComponent} from './welcome/welcome.component';
import {CustomerGridComponent} from './customer-grid/customer-grid.component';

import {AgGridModule} from 'ag-grid-angular';
import {HttpClientModule} from '@angular/common/http';
import {VetGridComponent} from './vet-grid/vet-grid.component';
import {VisitGridComponent} from './visit-grid/visit-grid.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    CustomerGridComponent,
    VetGridComponent,
    VisitGridComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AgGridModule.withComponents([])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
