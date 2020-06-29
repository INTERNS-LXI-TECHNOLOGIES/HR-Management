import { EmployeeAppraisalPageModule } from './../employee-appraisal/employee-appraisal.module';
import { AdduserPageModule } from './../adduser/adduser.module';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MenuPage } from './menu.page';
import { HomePageModule } from '../home/home.module';
import { LeavePageModule } from '../leave/leave.module';
import { LateArrivalPageModule } from '../late-arrival/late-arrival.module';
import { ReportStatusPageModule} from '../report-status/report-status.module';
import { EvaluationPageModule } from '../evaluation/evaluation.module';
import { EditUserPageModule } from '../edit-user/edit-user.module';
import { JiraPageModule } from 'src/app/Pages/jira/jira.module';
import { AuthGuard } from 'src/app/guard/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: MenuPage,
    canActivate: [AuthGuard],
    data: {
      role: 'ROLE_ADMIN'
    },
    children: [
      {
        path: 'home',
        loadChildren:  () => HomePageModule
      },
      {
        path: 'adduser',
        loadChildren: () => AdduserPageModule
      },
      {
        path: 'leave',
        loadChildren:  () => LeavePageModule
      },
      {
        path: 'late-arrival',
        loadChildren:  () => LateArrivalPageModule
      },
      {
        path: 'report-status',
        loadChildren:  () => ReportStatusPageModule
      },
      {
        path: 'evaluation',
        loadChildren:  () => EvaluationPageModule
      },
      {
        path: 'edit-user',
        loadChildren: () => EditUserPageModule
      },
      {
        path: 'employee-appraisal',
        loadChildren: () => EmployeeAppraisalPageModule
      },
      {
        path: 'jira',
        loadChildren: () => JiraPageModule
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MenuPageRoutingModule {}
