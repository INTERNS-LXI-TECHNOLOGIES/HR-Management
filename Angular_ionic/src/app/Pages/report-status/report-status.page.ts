import { Component, OnInit } from '@angular/core';
import { reportStatus } from 'src/model/ReportStatus';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Name } from 'src/model/Name';
import { AlertController } from '@ionic/angular';
@Component({
  selector: 'app-report-status',
  templateUrl: './report-status.page.html',
  styleUrls: ['./report-status.page.scss'],
})
export class ReportStatusPage implements OnInit {
  isValidFormSubmitted = false;
  name: Name[]=[
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
  model:reportStatus={
    name:"",  
    type:"",
    reportTime:"",
     
  }

  user;
  constructor(private alert: AlertController,private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }
  sendFeedback():void{

    console.log("COnsole test for late Model"+this.model.type+"REportTime = "+this.model.reportTime+" NAme of user ="+this.model.name);
    
    let Url:string = "http://localhost:8080/api/report-statuses";
   
    this.http.post(Url,this.model).subscribe(async data => {
      this.user=data;
      if(this.user==true)
      {
        alert('ReportStatus Already updated' );
      }
      else{
        const alertPrompt = await this.alert.create({
          cssClass: 'my-custom-class',
          header: 'ReportStatus Updated successfully',
          subHeader: '',
          message: '',
          buttons: ['OK']
        });
        await alertPrompt.present();
     
        this.router.navigateByUrl('/menu/home');

      }

     
    },

    async err => {
      const alertPrompt = await this.alert.create({
        cssClass: 'my-custom-class',
        header: 'Report-Status updation Failed',
        subHeader: ' please enter valid name',
        message: '',
        buttons: ['OK']
      });
      await alertPrompt.present();
      
      //alert("Leave Updation failed"+console.error() );
    });
   
  }   
}
