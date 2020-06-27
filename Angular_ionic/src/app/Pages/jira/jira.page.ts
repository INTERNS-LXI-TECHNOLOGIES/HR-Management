import { Component, OnInit } from '@angular/core';
import { jiraModel } from 'src/model/Jira';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
@Component({
  selector: 'app-jira',
  templateUrl: './jira.page.html',
  styleUrls: ['./jira.page.scss'],
})
export class JiraPage implements OnInit {
  model:jiraModel={
    name:"",
   
    hour:"",
     
  }
  user;
  constructor(private alert: AlertController,private http:HttpClient,
    private router:Router) { }

  ngOnInit() {
  }
  sendFeedback():void{

    console.log("COnsole test for JIRA = "+" HOUR = "+this.model.hour+"NAme of user ="+this.model.name);
    
    let Url:string = "http://localhost:8080/api/jiras";
   
    this.http.post(Url,this.model).subscribe(async data => {
      this.user=data;
      if(this.user==true)
      {
        alert('Jira hours Already updated' );
      }
      else{
        const alertPrompt = await this.alert.create({
          cssClass: 'my-custom-class',
          header: 'Jira Hour Updated successfully',
          subHeader: '',
          message: '',
          buttons: ['OK']
        });
        await alertPrompt.present();
     
        this.router.navigateByUrl('/menu/home');

      }
    },
    err=> {
      alert("JIRA hours Updation failed"+console.error() );
    });
      

    

  }
}
