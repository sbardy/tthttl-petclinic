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
import {OwnerFormComponent} from './owner-form/owner-form.component';
import {ReactiveFormsModule} from '@angular/forms';
import {PetFormComponent} from './pet-form/pet-form.component';
import {VetFormComponent} from './vet-form/vet-form.component';
import {VisitFormComponent} from './visit-form/visit-form.component';
import {RestClient} from './services/rest-client.service';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    CustomerGridComponent,
    VetGridComponent,
    VisitGridComponent,
    OwnerFormComponent,
    PetFormComponent,
    VetFormComponent,
    VisitFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AgGridModule.withComponents([]),
    ReactiveFormsModule
  ],
  providers: [RestClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
