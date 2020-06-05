import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { WorkProfilePage } from './work-profile.page';

const routes: Routes = [
  {
    path: '',
    component: WorkProfilePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class WorkProfilePageRoutingModule {}
