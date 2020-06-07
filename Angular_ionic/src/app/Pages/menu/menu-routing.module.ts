import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MenuPage } from './menu.page';
import { HomePageModule } from '../home/home.module';
import { AdduserPageModule } from '../adduser/adduser.module';
  import { from } from 'rxjs';

const routes: Routes = [
  {
    path: '',
    component: MenuPage,
    children:[
      {
        path: 'home',
        loadChildren:  ()=> HomePageModule
      },
      {
        path: 'adduser',
        loadChildren: ()=> AdduserPageModule
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MenuPageRoutingModule {}
