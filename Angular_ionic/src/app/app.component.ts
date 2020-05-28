import { Component, OnInit } from '@angular/core';
import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { AppraisalService } from './appraisal.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss']
})
export class AppComponent implements OnInit {
  public selectedIndex = 0;
  public appPages = [
    {
      title: 'Home',
      url: '/home',
      // icon: 'home'
    },
    {
      title: 'Status',
      url: '/status',
    },
    {
      title: 'Logout',
      url: '/logout',
      // icon: 'logo-buffer'
    },
    
   
  ];


  url:string='http://localhost:8080/api/appraisal-controller-resource/';
  value:any=this.appservice.getString('http://localhost:8080/api/appraisal-controller-resource/');

  constructor(private appservice: AppraisalService,
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar
  ) {
    this.initializeApp();
  }
  
 // user:any=this.appservice.getUsers();
  //users:any=this.appservice.getString(string);

  
  initializeApp() {
    this.platform.ready().then(() => {
    this.statusBar.styleDefault();
    this.splashScreen.hide();
    });
  }

  ngOnInit() {

    const path = window.location.pathname.split('folder/')[1];
    if (path !== undefined) {
      this.selectedIndex = this.appPages.findIndex(page => page.title.toLowerCase() === path.toLowerCase());
    }
  }
}