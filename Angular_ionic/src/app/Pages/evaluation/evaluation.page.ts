import { Component, OnInit } from '@angular/core';
import { gitModel } from 'src/model/Git';
import { hackModel } from 'src/app/model/Hack';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Name } from 'src/app/model/Name';
import { AlertController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';
@Component({
  selector: 'app-evaluation',
  templateUrl: './evaluation.page.html',
  styleUrls: ['./evaluation.page.scss'],
})
export class EvaluationPage implements OnInit {
  isValidFormSubmitted = false;
  name: Name[] = [
    { name:'sooraj'},
    { name:'meharu'},
    { name:'ajith'},
    { name:'abhi'},
    { name:'sanu'},
    { name:'jose'},
    { name:'mani'},
    { name:'anju'},
    { name:'gokhul'},
  ];
  modelGit: gitModel = {
    name: '',
    mark: '',
  };
  modelHack: hackModel = {
    name: '',
    mark: ''
  };
  user;
  constructor(private alert: AlertController,
              private http: HttpClient,
              private router: Router,
              private translate: TranslateService) { }

  ngOnInit() {
  }
  sendFeedback():void{

    console.log("COnsole test for GIT Model"+this.modelGit.name+"GIT MARK = "+this.modelGit.mark);
    let Url:string = "http://localhost:8080/api/gits";
    this.http.post(Url, this.modelGit).subscribe(async data => 
    {
        this.user=data;
        if(this.user==true)
        {
          alert('Git score Already updated' );
        }
        else{
          const alertPrompt = await this.alert.create({
            cssClass: 'my-custom-class',
            header: this.translate.instant('GIT-ALERT.header'),
            buttons: [this.translate.instant('ALERT.OK')]
          });
          await alertPrompt.present();
          this.router.navigateByUrl('/menu/home');
        }
    },
    async err => {
      const alertPrompt = await this.alert.create({
        cssClass: 'my-custom-class',
        header: this.translate.instant('GIT_ERROR-ALERT.header'),
        buttons: [this.translate.instant('ALERT.OK')]
      });
      await alertPrompt.present();
    });
    console.log("COnsole test for Hack Model"+this.modelHack.name+"HACKATHON MARK = "+this.modelHack.mark);
    let Url1:string = "http://localhost:8080/api/hackathons";
   
    this.http.post(Url1,this.modelHack).subscribe( async data => {
      this.user=data;
      if(this.user==true)
      {
        alert('Hackathon score Already updated' );
      }
      else{
        const alertPrompt = await this.alert.create({
          cssClass: 'my-custom-class',
          header: this.translate.instant('HACK-ALERT.header'),
          buttons: [this.translate.instant('ALERT.OK')]
        });
        await alertPrompt.present();
     
        this.router.navigateByUrl('/menu/home');

      }
    },
    async err => {
      const alertPrompt = await this.alert.create({
        cssClass: 'my-custom-class',
        header: this.translate.instant('HACK_ERROR-ALERT.header'),
        buttons: [this.translate.instant('ALERT.OK')]
      });
      await alertPrompt.present();
    });
  }
}