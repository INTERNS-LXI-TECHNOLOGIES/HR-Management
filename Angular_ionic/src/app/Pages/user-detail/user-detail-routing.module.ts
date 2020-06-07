import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserDetailPage } from './user-detail.page';

const routes: Routes = [
  {
  path: ':id',
  component: UserDetailPage,
 children: [
  {
    path: 'work-profile',
    children: [
      {
        path: 'work-profile',
        loadChildren: () => import('src/app/Pages/work-profile/work-profile.module').then( m => m.WorkProfilePageModule)
      },
    ]
  },
]
}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserDetailPageRoutingModule {}
