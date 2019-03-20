import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CustomerGridComponent} from './customer-grid/customer-grid.component';
import {VetGridComponent} from './vet-grid/vet-grid.component';
import {VisitGridComponent} from './visit-grid/visit-grid.component';

const routes: Routes = [
  {
    path: 'customers', component: CustomerGridComponent
  },
  {
    path: 'vets', component: VetGridComponent
  },
  {
    path: 'visits', component: VisitGridComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
