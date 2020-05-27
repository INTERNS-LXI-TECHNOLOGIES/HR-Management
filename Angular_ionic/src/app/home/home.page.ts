import { Component, OnInit } from '@angular/core';
import { AppraisalService } from '../appraisal.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  
  users:any=this.appservice.getUsers('http://localhost:8080/api/users/');
  constructor(private appservice: AppraisalService,private router: Router){

  }
  
  navigate(){
    this.router.navigate(['/detail'])
  }
  ngOnInit() {
  }

}
