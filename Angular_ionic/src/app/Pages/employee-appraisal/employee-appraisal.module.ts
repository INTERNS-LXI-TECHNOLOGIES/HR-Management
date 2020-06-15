import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EmployeeAppraisalPageRoutingModule } from './employee-appraisal-routing.module';

import { EmployeeAppraisalPage } from './employee-appraisal.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EmployeeAppraisalPageRoutingModule
  ],
  declarations: [EmployeeAppraisalPage]
})
export class EmployeeAppraisalPageModule {}
