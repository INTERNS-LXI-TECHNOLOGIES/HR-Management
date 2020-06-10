import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.page.html',
  styleUrls: ['./user-detail.page.scss'],
})
export class UserDetailPage implements OnInit {
   user;
   id;
   image;
   error = '';
   public img;
  constructor(private route: ActivatedRoute, private router: Router, private sanitizer: DomSanitizer,
              private userService: UserService , private httpClient: HttpClient) {    }
  ngOnInit(){
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.userService.getUser('http://localhost:8080/api/appraisal-controller-resource/user-extras/' + this.id)
                                .subscribe(user => this.user = user);
      this.userService.getImage('http://localhost:8080/api/appraisal-controller-resource/image/' + this.id)
                                .subscribe(image => {this.image = image; },
                                  (error: any) => {console.log(error); } );
      console.log(this.image);
    });
}
  get(id: string)
  {
    this.router.navigate(['/menu/home/user-info/work-profile', id]);
  }
}
