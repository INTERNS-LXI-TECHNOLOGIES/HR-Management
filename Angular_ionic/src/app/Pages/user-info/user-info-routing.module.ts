import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserInfoPage } from './user-info.page';

const routes: Routes = [
  {
    path: ':id',
    component: UserInfoPage,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserInfoPageRoutingModule {}
