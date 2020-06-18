import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { JiraPage } from './jira.page';

const routes: Routes = [
  {
    path: '',
    component: JiraPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class JiraPageRoutingModule {}
