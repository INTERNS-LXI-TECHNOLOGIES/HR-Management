import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.page.html',
  styleUrls: ['./adduser.page.scss'],
})
export class AdduserPage implements OnInit {
  model:userViewModel={
    firstName:"",
    lastName:"",
    company:"",
    email:"",
    position:"",
    authorities:"",  
    joiningDate:"",
    dob:"",
    image:"",
    login:"",
    password:""

  }
  user: Object;
  constructor(private http:HttpClient,
              private router:Router) { }

  ngOnInit() {
  }
  sendFeedback():void{
    
    let Url:string = "http://localhost:8080/api/appraisal-controller-resource/addUser";
    // alert(this.model.name);
    this.http.post(Url,this.model).subscribe(data => {
      alert("user added");
      this.router.navigateByUrl('/home');

    },
    err=> {
      alert("something went wrong"+console.error() );
    });
      

    

  }
}
export interface userViewModel{

  firstName:string;
  lastName:string;
  company:string;
  email:string;
  position:string;
  authorities:string;  
  joiningDate;
  dob;
  image;
  login:string;
  password:string;

}