import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { WorkProfilePageRoutingModule } from './work-profile-routing.module';

import { WorkProfilePage } from './work-profile.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    WorkProfilePageRoutingModule
  ],
  declarations: [WorkProfilePage]
})
export class WorkProfilePageModule {}
