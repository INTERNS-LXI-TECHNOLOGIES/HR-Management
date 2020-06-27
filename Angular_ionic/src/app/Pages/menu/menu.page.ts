import { AuthServerProvider } from './../../services/auth/auth-jwt.service';
import { Component, OnInit } from '@angular/core';
import { Router, RouterEvent } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.page.html',
  styleUrls: ['./menu.page.scss'],
})
export class MenuPage implements OnInit {

  public appPages = [
    {
      title: 'Home',
      url: '/menu/home',
      icon: 'home'
    },
    {
      title: 'Add user',
      url: '/menu/adduser',
      icon: 'add'
    },
    {
      title: 'Appraisal Report',
      url: '/menu/employee-appraisal',
      icon: 'book'
    },
  ];
  selectedPath = '';
  constructor(private router: Router, private authServerProvider: AuthServerProvider) {
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
