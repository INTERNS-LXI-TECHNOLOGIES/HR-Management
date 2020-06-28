import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {FormControl} from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import {leaveModel } from 'src/app/model/Leave';
import { type } from 'os';
import {Name } from 'src/app/model/Name';
import { AppraisalService } from 'src/app/appraisal.service';
import { UserService } from 'src/app/service/user.service';
import { userViewModel } from 'src/app/model/User';
import { AlertController } from '@ionic/angular';
@Component({
  selector: 'app-leave',
  templateUrl: './leave.page.html',
  styleUrls: ['./leave.page.scss'],
})



export class LeavePage implements OnInit {

  users: any = this.appservice.getUsers('http://localhost:8080/api/appraisal-controller-resource/userName');

 
  name: Name[]=[
    
    { name:'abhi'},
    
  ];

  model:leaveModel={
    name:"",
    type:"",
    leaveDate:"",
    
     
  }
  

  user;
  constructor( private alert: AlertController,private appservice: AppraisalService, private route: ActivatedRoute,private userService: UserService,private http: HttpClient , private router: Router) 
  { }
 

  ngOnInit() {
  }
  sendFeedback():void{

    console.log("COnsole test for leave Model"+this.model.type+this.model.leaveDate+"NAme of user ="+this.model.name);
    
    let Url:string = "http://localhost:8080/api/leaves";
   
    this.http.post(Url,this.model).subscribe(async data => {
     // alert("Leave Updated successfully");
      this.user=data;
      if(this.user==true)
      {
        alert('leave Already updated' );
      }
      else{
        const alertPrompt = await this.alert.create({
          cssClass: 'my-custom-class',
          header: 'Leave Updated successfully',
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
        header: 'Leave updation Failed',
        subHeader: ' please enter valid name',
        message: '',
        buttons: ['OK']
      });
      await alertPrompt.present();
      
      //alert("Leave Updation failed"+console.error() );
    });
  }
}
