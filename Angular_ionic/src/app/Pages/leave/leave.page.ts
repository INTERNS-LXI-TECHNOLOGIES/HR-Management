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
@Component({
  selector: 'app-leave',
  templateUrl: './leave.page.html',
  styleUrls: ['./leave.page.scss'],
})



export class LeavePage implements OnInit {

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

  model:leaveModel={
    name:"",
    type:"",
    leaveDate:"",
    
     
  }
  

  user: Object;
  constructor(private appservice: AppraisalService, private route: ActivatedRoute,private userService: UserService,private http: HttpClient , private router: Router) 
  { }
  users: Observable <userViewModel> = this.appservice.getUsers('http://localhost:8080/api/appraisal-controller-resource/userName');

  ngOnInit() {
  }
  sendFeedback():void{

    console.log("COnsole test for leave Model"+this.model.type+this.model.leaveDate+"NAme of user ="+this.model.name);
    
    let Url:string = "http://localhost:8080/api/leaves";
   
    this.http.post(Url,this.model).subscribe(data => {
      alert("Leave Updated successfully");

    },
    err => {
      alert("Leave Updation failed"+console.error() );
    });
   this.router.navigateByUrl('/menu/home');
  }
}
