import { Component, OnInit } from '@angular/core';
import {lateArrival } from 'src/app/model/lateArrival';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
@Component({
  selector: 'app-late-arrival',
  templateUrl: './late-arrival.page.html',
  styleUrls: ['./late-arrival.page.scss'],
})
export class LateArrivalPage implements OnInit {
  model:lateArrival={
    name:"",
    type:"Authorized",
    lateTime:"",
     
  }
  user: Object;
  constructor(private http:HttpClient,
              private router:Router) { }
  ngOnInit() {
  }
  sendFeedback():void{

    console.log("COnsole test for late Model TYPE = "+this.model.type+" REACHED TIME = "+this.model.lateTime+"NAme of user ="+this.model.name);
    
    let Url:string = "http://localhost:8080/api/late-arrivals";
   
    this.http.post(Url,this.model).subscribe(data => {
      alert("LateArrival Updated successfully");

    },
    err=> {
      alert("LateArrival Updation failed"+console.error() );
    });
      

    this.router.navigateByUrl('/home');

  }

}