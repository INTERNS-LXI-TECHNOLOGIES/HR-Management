import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { AppraisalDetailsPageRoutingModule } from './appraisal-details-routing.module';
import { AppraisalDetailsPage } from './appraisal-details.page';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AppraisalDetailsPageRoutingModule,
    TranslateModule
  ],
  declarations: [AppraisalDetailsPage]
})
export class AppraisalDetailsPageModule {}
