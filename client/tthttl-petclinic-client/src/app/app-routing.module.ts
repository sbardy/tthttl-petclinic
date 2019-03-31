import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CustomerGridComponent} from './customer-grid/customer-grid.component';
import {VetGridComponent} from './vet-grid/vet-grid.component';
import {VisitGridComponent} from './visit-grid/visit-grid.component';
import {OwnerFormComponent} from './owner-form/owner-form.component';
import {PetFormComponent} from './pet-form/pet-form.component';
import {VetFormComponent} from './vet-form/vet-form.component';
import {VisitFormComponent} from './visit-form/visit-form.component';
import {WelcomeComponent} from './welcome/welcome.component';

const routes: Routes = [
  {
    path: 'customers', component: CustomerGridComponent
  },
  {
    path: 'vets', component: VetGridComponent
  },
  {
    path: 'visits', component: VisitGridComponent
  },
  {
    path: 'form/owners/:id', component: OwnerFormComponent
  },
  {
    path: 'form/owners', component: OwnerFormComponent
  },
  {
    path: 'form/pets/:id', component: PetFormComponent
  },
  {
    path: 'form/pets', component: PetFormComponent
  },
  {
    path: 'form/vets/:id', component: VetFormComponent
  },
  {
    path: 'form/vets', component: VetFormComponent
  },
  {
    path: 'form/visits/:id', component: VisitFormComponent
  },
  {
    path: 'form/visits', component: VisitFormComponent
  },
  {
    path: '**', component: WelcomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
