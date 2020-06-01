import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/User';
import { Router } from '@angular/router';
import { Leave } from 'src/app/model/Leave';
@Component({
  selector: 'app-leave',
  templateUrl: './leave.page.html',
  styleUrls: ['./leave.page.scss'],
})
export class LeavePage implements OnInit {
  model:leaveModel={
    Name:"",
    type:"",
    leaveDate:"",
    
  }

  user: Object;
  constructor(private http:HttpClient,
              private router:Router) { }

  ngOnInit() {
  }
  sendFeedback():void{
    
    let Url:string = "http://localhost:8080/api/leaves";
   
    this.http.post(Url,this.model).subscribe(data => {
      alert("Leave Updated successfully");

    },
    err=> {
      alert("Leave Updation failed"+console.error() );
    });
      

    this.router.navigateByUrl('/home');

  }
}
export interface leaveModel{

   Name:string,
   type:string,
   leaveDate;

}
