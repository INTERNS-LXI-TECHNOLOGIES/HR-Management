import { EmployeeAppraisalPageModule } from './../employee-appraisal/employee-appraisal.module';
import { AppraisalDetailsPageModule } from './../appraisal-details/appraisal-details.module';
import { AdduserPageModule } from './../adduser/adduser.module';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MenuPage } from './menu.page';
import { HomePageModule } from '../home/home.module';
import { LeavePageModule } from '../leave/leave.module';
import { LateArrivalPageModule } from '../late-arrival/late-arrival.module';
import { ReportStatusPageModule} from '../report-status/report-status.module';
import { EvaluationPageModule } from '../evaluation/evaluation.module';
import { UserInfoPageModule } from '../user-info/user-info.module';
import { WorkProfilePageModule } from '../work-profile/work-profile.module';
import { EditUserPageModule } from '../edit-user/edit-user.module';

const routes: Routes = [
  {
    path: '',
    component: MenuPage,
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
        path: 'home/user-info',
        loadChildren: () => UserInfoPageModule
      },
      {
        path: 'edit-user',
        loadChildren: () => EditUserPageModule
      },
      {
        path: 'home/work-profile',
        loadChildren: () => WorkProfilePageModule
      },
      {
        path: 'appraisal',
        loadChildren: () => AppraisalDetailsPageModule
      },
      {
        path: 'employee-appraisal',
        loadChildren: () => EmployeeAppraisalPageModule
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MenuPageRoutingModule {}
