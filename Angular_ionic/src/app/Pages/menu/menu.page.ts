import { PopoverController } from '@ionic/angular';
import { AuthServerProvider } from './../../services/auth/auth-jwt.service';
import { Component, OnInit } from '@angular/core';
import { Router, RouterEvent } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.page.html',
  styleUrls: ['./menu.page.scss'],
})
export class MenuPage implements OnInit {

  public appPages = [
    {
      title: this.translate.instant('SIDE-MENU.Home'),
      url: '/menu/home',
      icon: 'home'
    },
    {
      title: this.translate.instant('SIDE-MENU.Add User'),
      url: '/menu/adduser',
      icon: 'add'
    },
    {
      title: this.translate.instant('SIDE-MENU.Appraisal'),
      url: '/menu/employee-appraisal',
      icon: 'book'
    },
  ];
  selectedPath = '';
  constructor(private router: Router, private authServerProvider: AuthServerProvider,
              private popoverCtrl: PopoverController, private translate: TranslateService) {
     this.router.events.subscribe((event: RouterEvent) => {
       if (event && event.url){
         this.selectedPath = event.url;

       }
     });
   }

  ngOnInit() {
  }
  logout()
  {
    this.authServerProvider.logout();
  }

}
