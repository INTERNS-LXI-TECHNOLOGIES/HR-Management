import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { LoginService } from './../../services/login/login.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-popover-component',
  templateUrl: './popover-component.page.html',
  styleUrls: ['./popover-component.page.scss'],
})
export class PopoverComponentPage implements OnInit {
  id = this.userService.getId();
  constructor(private router: Router,
              private userService: UserService,
              private loginService: LoginService,) { }

  ngOnInit() {
  }
  getWorkProfile()
  {
    this.router.navigate(['work-profile', this.id]);
  }
  logout()
  {
    this.loginService.logout();
  }
}
