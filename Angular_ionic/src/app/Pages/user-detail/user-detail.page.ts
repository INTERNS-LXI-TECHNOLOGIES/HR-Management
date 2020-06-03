import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.page.html',
  styleUrls: ['./user-detail.page.scss'],
})
export class UserDetailPage implements OnInit {
   user;
   userExtra; 

  constructor(private route: ActivatedRoute,
    private userService: UserService,private httpClient: HttpClient) {    }
    value:any=this.route.queryParams.subscribe((res)=>{
      console.log(res);});
     
  
  ngOnInit() {
    
    this.route.params.subscribe(params => {
      const id= params['id'];
      this.userService.getUser('http://localhost:8080/api/appraisal-controller-resource/user-extras/'+id)
                                .subscribe(user => this.user = user); 
    });
    
  }

}
