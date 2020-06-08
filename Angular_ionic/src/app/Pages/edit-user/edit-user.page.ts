import { UserService } from './../../service/user.service';
import { ActivatedRoute } from '@angular/router';
import { AppraisalService } from 'src/app/appraisal.service';
import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/compiler/src/core';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.page.html',
  styleUrls: ['./edit-user.page.scss'],
})
export class EditUserPage implements OnInit {

  constructor(private route: ActivatedRoute,
              private userService: UserService,
    ) { }
  id: 0;
  user;
  ngOnInit() {
    this.route.params.subscribe(params => {
      // tslint:disable-next-line: no-string-literal
      this.id = params['id'];
      alert('id:   is' + params['id']); });
    this.userService.getUser('http://localhost:8080/api/appraisal-controller-resource/user-extras/' + this.id)
                                .subscribe(user => this.user = user);
   
  }

}
