import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { LateArrivalPageRoutingModule } from './late-arrival-routing.module';
import { LateArrivalPage } from './late-arrival.page';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    LateArrivalPageRoutingModule,
    TranslateModule
  ],
  declarations: [LateArrivalPage]
})
export class LateArrivalPageModule {}
