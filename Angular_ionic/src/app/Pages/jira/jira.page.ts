import { Component, OnInit } from '@angular/core';
import { jiraModel } from 'src/app/model/Jira';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

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
  constructor(private http:HttpClient,
    private router:Router) { }

  ngOnInit() {
  }
  sendFeedback():void{

    console.log("COnsole test for JIRA = "+" HOUR = "+this.model.hour+"NAme of user ="+this.model.name);
    
    let Url:string = "http://localhost:8080/api/jira";
   
    this.http.post(Url,this.model).subscribe(data => {
      alert("JIRA hours Updated successfully");

    },
    err=> {
      alert("JIRA hours Updation failed"+console.error() );
    });
      

    this.router.navigateByUrl('/menu/home');

  }
}
