import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EmployeeAppraisalPage } from './employee-appraisal.page';

const routes: Routes = [
  {
    path: '',
    component: EmployeeAppraisalPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EmployeeAppraisalPageRoutingModule {}
