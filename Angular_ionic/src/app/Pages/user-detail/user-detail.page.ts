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
  constructor(private route: ActivatedRoute,
    private userService: UserService) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      const username = params['username'];
      this.userService.getUser('http://localhost:8080/api/appraisal-controller-resource/'+username)
                                .subscribe(user => this.user = user);
         
    });
  }

}
