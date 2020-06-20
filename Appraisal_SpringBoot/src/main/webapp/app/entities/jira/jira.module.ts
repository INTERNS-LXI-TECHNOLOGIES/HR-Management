import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppraisalSharedModule } from 'app/shared/shared.module';
import { JiraComponent } from './jira.component';
import { JiraDetailComponent } from './jira-detail.component';
import { JiraUpdateComponent } from './jira-update.component';
import { JiraDeleteDialogComponent } from './jira-delete-dialog.component';
import { jiraRoute } from './jira.route';

@NgModule({
  imports: [AppraisalSharedModule, RouterModule.forChild(jiraRoute)],
  declarations: [JiraComponent, JiraDetailComponent, JiraUpdateComponent, JiraDeleteDialogComponent],
  entryComponents: [JiraDeleteDialogComponent],
})
export class AppraisalJiraModule {}
