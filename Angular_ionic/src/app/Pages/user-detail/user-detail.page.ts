import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.page.html',
  styleUrls: ['./user-detail.page.scss'],
})
export class UserDetailPage implements OnInit {
   user;
   id;
   base64image: string;
  constructor(private route: ActivatedRoute, private router: Router,
              private userService: UserService , private httpClient: HttpClient) {    }
  ngOnInit(){
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.userService.getUser('http://localhost:8080/api/appraisal-controller-resource/user-extras/' + this.id)
                                .subscribe(user => this.user = user);
    });
}
  get(id: string)
  {
    this.router.navigate(['/menu/home/user-info/work-profile', id]);
  }
  value: any=console.log(this.user.imageContentType);
}
