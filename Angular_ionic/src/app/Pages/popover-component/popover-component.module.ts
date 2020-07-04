import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { PopoverComponentPageRoutingModule } from './popover-component-routing.module';
import { PopoverComponentPage } from './popover-component.page';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PopoverComponentPageRoutingModule,
    TranslateModule
  ],
  declarations: [PopoverComponentPage]
})
export class PopoverComponentPageModule {}
