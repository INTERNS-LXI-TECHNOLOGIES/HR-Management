import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppraisalDetailsPage } from './appraisal-details.page';

const routes: Routes = [
  {
    path: ':id',
    component: AppraisalDetailsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AppraisalDetailsPageRoutingModule {}
