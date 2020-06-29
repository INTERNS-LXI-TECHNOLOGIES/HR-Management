import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { AdduserPageRoutingModule } from './adduser-routing.module';
import { AdduserPage } from './adduser.page';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AdduserPageRoutingModule,
    TranslateModule
  ],
  declarations: [AdduserPage]
})
export class AdduserPageModule {}
