import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerGridComponent } from './customer-grid/customer-grid.component';

const routes: Routes = [
  {
    path: 'customers', component: CustomerGridComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
