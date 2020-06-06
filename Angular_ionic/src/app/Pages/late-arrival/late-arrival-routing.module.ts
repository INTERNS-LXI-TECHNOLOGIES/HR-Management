import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LateArrivalPage } from './late-arrival.page';

const routes: Routes = [
  {
    path: '',
    component: LateArrivalPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LateArrivalPageRoutingModule {}
