import { LoginService } from './../../services/login/login.service';
import { Component, OnInit } from '@angular/core';
import { AppraisalService } from 'src/app/appraisal.service';
import { Router, ActivatedRoute, NavigationExtras } from '@angular/router';
import { UserService } from '../../service/user.service';
import { HttpClient } from '@angular/common/http';
import { userViewModel } from 'src/app/model/User';
import { Observable } from 'rxjs';
import { AuthServerProvider } from './../../services/auth/auth-jwt.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  id;
  constructor(private appservice: AppraisalService, private router: Router, private httpClient: HttpClient,
              private userService: UserService, private route: ActivatedRoute,
              private loginService: LoginService){  }
  users: Observable <userViewModel> = this.appservice.getUsers('http://localhost:8080/api/users/');
  public deleteUser(id: string){
    // tslint:disable-next-line: deprecation
    event.stopImmediatePropagation();
    this.appservice.deleteUser(id);
    setTimeout(() => {
      this.users = this.appservice.getUsers('http://localhost:8080/api/users/');
    }, 2000);
  }
  public editUser(id: string){
    // tslint:disable-next-line: deprecation
    event.stopImmediatePropagation();
    this.router.navigate(['menu/edit-user', id]);
  }

  ngOnInit() {
  }
  select(id: string){
    this.userService.setId(id);
    this.router.navigate(['/menu/home/user-info', id]);

  }
  signout()
  {
    this.loginService.logout();
  }
}
