import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserInfoPage } from './user-info.page';
import { UserDetailPageModule } from '../user-detail/user-detail.module';
import { WorkProfilePageModule } from '../work-profile/work-profile.module';

const routes: Routes = [
  {
    path: '',
    component: UserInfoPage,
    children: [
      {
        path: 'user-detail',
        loadChildren: () => UserDetailPageModule
      },
      {
        path: 'work-profile',
        loadChildren: () => WorkProfilePageModule
      },
    ]
  },
  {
    path: '',
    redirectTo: 'user-info/user-detail',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserInfoPageRoutingModule {}
