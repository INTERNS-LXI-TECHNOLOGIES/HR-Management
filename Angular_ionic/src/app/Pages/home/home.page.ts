import { Component, OnInit } from '@angular/core';
import { AppraisalService } from 'src/app/appraisal.service';
import { Router, ActivatedRoute, NavigationExtras } from '@angular/router';
import { UserService } from '../../service/user.service';
import { HttpClient } from '@angular/common/http';
import { userViewModel } from 'src/app/model/User';
import { Observable } from 'rxjs';
import { AppraisalControllerResourceService } from './../../api/services';
import { UserDTO } from 'src/app/api/models';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  id;
  constructor(private appservice: AppraisalService, private router: Router, private httpClient: HttpClient,
              private userService: UserService, private route: ActivatedRoute,
              private appCntl: AppraisalControllerResourceService, ){  }
  users: Observable <UserDTO[]> = this.appCntl.getAllUserUsingGET();
  public deleteUser(id: string){
    // tslint:disable-next-line: deprecation
    event.stopImmediatePropagation();
    this.appservice.deleteUser(id);
    setTimeout(() => {
      this.users = this.appCntl.getAllUserUsingGET();
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
}
