import { Component, OnInit } from '@angular/core';
import { AppraisalService } from 'src/app/appraisal.service';
import { UserDetailPage } from '../user-detail/user-detail.page';
import { Router } from '@angular/router';
import { UserService }from '../../service/user.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NavController } from '@ionic/angular';
import { NgZone } from '@angular/core';




@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  private counter = 0;
  
  users:any=this.appservice.getUsers('http://localhost:8080/api/users/');
  constructor(private appservice: AppraisalService,private router: Router,private httpClient: HttpClient,
    private navCtrl:NavController,private zone: NgZone){
    
  }
  
  public deleteUser(id:string){
    event.stopImmediatePropagation();
    this.counter += 1;
    let url:string = "http://localhost:8080/api/user-extras/"+id;
    this.httpClient.delete(url).subscribe(data=>{
      alert("User removed Succesfully..!" );
      
    
     
    },
    err=> {
      alert("something went wromg..!" ); 
    });
    this.router.navigate(['/home/',this.counter]);
  }
  public editUser(id:string){
    event.stopImmediatePropagation();
    this.router.navigate(['/edit-user',id]);
  }

  ngOnInit() {
    // this.router.navigate(['/user-detail'])

    // 
  }
  select(id:string){
    this.router.navigate(['/user-detail',id]);

  }

}
