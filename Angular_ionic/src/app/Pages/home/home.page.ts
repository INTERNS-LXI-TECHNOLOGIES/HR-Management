import { Component, OnInit } from '@angular/core';
import { AppraisalService } from 'src/app/appraisal.service';
import { UserDetailPage } from '../user-detail/user-detail.page';
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
  
  public test(user)
  {
    this.router.navigate(['/user-detail'],{
      queryParams: user,
      });
  
  }
  ngOnInit() {

    
  }

}
