import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { JiraPageRoutingModule } from './jira-routing.module';
import { JiraPage } from './jira.page';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    JiraPageRoutingModule,
    TranslateModule
  ],
  declarations: [JiraPage]
})
export class JiraPageModule {}
