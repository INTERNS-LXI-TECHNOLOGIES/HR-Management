import { WorkProfilePageModule } from './../work-profile/work-profile.module';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserInfoPage } from './user-info.page';

const routes: Routes = [
  {
    path: ':id',
    component: UserInfoPage,
    children: [
      {
        path: 'work-profile',
        loadChildren:  () => WorkProfilePageModule
      },
      {
        path: 'appraisal',
        loadChildren:  () => WorkProfilePageModule
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserInfoPageRoutingModule {}
