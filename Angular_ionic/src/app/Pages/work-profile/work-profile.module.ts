import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { WorkProfilePageRoutingModule } from './work-profile-routing.module';
import { WorkProfilePage } from './work-profile.page';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    WorkProfilePageRoutingModule,
    TranslateModule
  ],
  declarations: [WorkProfilePage]
})
export class WorkProfilePageModule {}
