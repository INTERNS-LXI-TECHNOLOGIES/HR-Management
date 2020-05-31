import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.page.html',
  styleUrls: ['./user-detail.page.scss'],
})
export class UserDetailPage implements OnInit {
   user;
   userExtra;
  //  value:any;

   

  constructor(private route: ActivatedRoute,
    private userService: UserService) {    }
    value:any=this.route.queryParams.subscribe((res)=>{
      console.log(res);});
     
  
  ngOnInit() {
    // this.userExtra= this.userService.getUserExtra('http://localhost:8080/api/appraisal-controller-resource/user-extras/'+[this.value])
                                // .subscribe(userExtra => this.userExtra = userExtra); 
 
    // this.route.params.subscribe(params => {
    //   const id= params['id'];
    //   this.userService.getUserExtra('http://localhost:8080/api/user-extras/'+id)
    //                             .subscribe(userExtra => this.userExtra = userExtra);   
    // });
    this.route.params.subscribe(params => {
      const username = params['username'];
      this.userService.getUser('http://localhost:8080/api/users/'+username)
                                .subscribe(user => this.user = user);  
    });
  }

}
