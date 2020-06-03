import { Component, OnInit } from '@angular/core';
import { AppraisalService } from 'src/app/appraisal.service';
import { UserDetailPage } from '../user-detail/user-detail.page';
import { Router } from '@angular/router';
import { UserService }from '../../service/user.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NavController } from '@ionic/angular';
import { NgZone } from '@angular/core';
import { userViewModel } from 'src/app/model/User';




@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  
  
  users:userViewModel[]=this.appservice.getUsers('http://localhost:8080/api/users/');
  constructor(private appservice: AppraisalService,private router: Router,private httpClient: HttpClient,
    private userService:UserService){
    
  }
  
  public deleteUser(id:string){
    event.stopImmediatePropagation();
    
    this.appservice.deleteUser(id);
    // this.users=this.appservice.getUsers('http://localhost:8080/api/users/');
    
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
