import { Component, OnInit } from '@angular/core';
import {lateArrival } from 'src/model/lateArrival';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { Name } from 'src/app/model/Name';
import { TranslateService } from '@ngx-translate/core';
@Component({
  selector: 'app-late-arrival',
  templateUrl: './late-arrival.page.html',
  styleUrls: ['./late-arrival.page.scss'],
})
export class LateArrivalPage implements OnInit {
  isValidFormSubmitted = false;
  model: lateArrival = {
    name: '',
    type: '',
    reachedTime: '',
  }
  user;
  constructor( private alert: AlertController,
               private http: HttpClient,
               private router: Router,
               private translate: TranslateService) { }
  ngOnInit() {
  }
  sendFeedback(): void{
    console.log("COnsole test for late Model TYPE = "+this.model.type+" REACHED TIME = "+this.model.reachedTime+"NAme of user ="+this.model.name);
    let Url:string = "http://localhost:8080/api/late-arrivals";
   
    this.http.post(Url,this.model).subscribe(async data => {
      //alert("LateArrival Updated successfully");
      this.user = data;
      if (this.user == true)
      {
        alert('LateArrival Already updated' );
      }
      else{
        const alertPrompt = await this.alert.create({
          cssClass: 'my-custom-class',
          header: this.translate.instant('LATE-ALERT.header'),
          buttons: [this.translate.instant('ALERT.OK')]
        });
        await alertPrompt.present();
        this.router.navigateByUrl('/menu/home');
      }
    },
    async err => {
      const alertPrompt = await this.alert.create({
        cssClass: 'my-custom-class',
        header: this.translate.instant('LATE_ERROR-ALERT.header'),
        subHeader: this.translate.instant('LATE_ERROR-ALERT.subHeader'),
        buttons: [this.translate.instant('ALERT.OK')]
      });
      await alertPrompt.present();
      //alert("Leave Updation failed"+console.error() );
    });
  }
}
