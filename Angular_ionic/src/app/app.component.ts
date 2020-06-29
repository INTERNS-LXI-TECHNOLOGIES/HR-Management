import { Component, OnInit } from '@angular/core';
import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { AppraisalService } from './appraisal.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { LanguageService } from './services/language/language.service';
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss']
})
export class AppComponent implements OnInit {


  constructor(private appservice: AppraisalService,
              private platform: Platform,
              private splashScreen: SplashScreen,
              private statusBar: StatusBar,
              private languageService: LanguageService
  ) {
    this.initializeApp();
  }
  
  initializeApp() {
    this.platform.ready().then(() => {
    this.statusBar.styleDefault();
    this.splashScreen.hide();

    this.languageService.setInitialAppLanguage();
    });
  }

  ngOnInit() {

  }
}