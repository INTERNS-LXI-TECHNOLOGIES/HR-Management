import { Component, OnInit } from '@angular/core';
import { reportStatus } from 'src/app/model/ReportStatus';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-report-status',
  templateUrl: './report-status.page.html',
  styleUrls: ['./report-status.page.scss'],
})
export class ReportStatusPage implements OnInit {
  model:reportStatus={
    name:"",  
    type:"",
    reportTime:"",
     
  }

  user: Object;
  constructor(private http:HttpClient,
              private router:Router) { }

  ngOnInit() {
  }
  sendFeedback():void{

    console.log("COnsole test for late Model"+this.model.type+"REportTime = "+this.model.reportTime+" NAme of user ="+this.model.name);
    
    let Url:string = "http://localhost:8080/api/report-statuses";
   
    this.http.post(Url,this.model).subscribe(data => {
      alert("ReportStatus Updated successfully");

    },
    err=> {
      alert("ReportStatus Updation failed"+console.error() );
    });
      

    this.router.navigateByUrl('/home');

  }
}
