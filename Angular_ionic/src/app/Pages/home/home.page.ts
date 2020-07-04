import { LoginService } from './../../services/login/login.service';
import { Component, OnInit } from '@angular/core';
import { AppraisalService } from 'src/app/appraisal.service';
import { Router, ActivatedRoute, NavigationExtras } from '@angular/router';
import { UserService } from '../../service/user.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthServerProvider } from './../../services/auth/auth-jwt.service';
import { AppraisalControllerResourceService } from './../../api/services';
import { UserDTO } from 'src/app/model/models';
import { ThemeService } from 'src/app/theme.service';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
// tslint:disable-next-line: component-class-suffix
export class HomePage implements OnInit {
  id;
  constructor(private appservice: AppraisalService,
              private router: Router,
              private httpClient: HttpClient,
              private userService: UserService,
              private route: ActivatedRoute,
              private themeService: ThemeService,
              private loginService: LoginService,
              private appCntl: AppraisalControllerResourceService,
              private alert: AlertController, ){  }
  // users: Observable <userViewModel> = this.appservice.getUsers('http://localhost:8080/api/users/');
  users: Observable <UserDTO[]> = this.appCntl.getAllUserUsingGET();
  public async deleteUser(id: string){
    // tslint:disable-next-line: deprecation
    event.stopImmediatePropagation();
    const alertPrompt = await this.alert.create({
      cssClass: 'my-custom-class',
      header: 'Warning',
      subHeader: 'Confirmation',
      message: 'Are you sure to Delete...data may be lost..!',
      buttons: [
        {
          text: 'Okay',
            handler: () => {
              this.appservice.deleteUser(id);
              setTimeout(() => {
                this.users = this.appCntl.getAllUserUsingGET();
              }, 1000);
            }
        },
        {
            text: 'Cancel',
            handler: () => {
              console.log('Cancel: user deletion');
            }
         }
      ]
    });
    alertPrompt.present();
  }
  
  // userDelete(id){
  //   // tslint:disable-next-line: deprecation
  //   // tslint:disable-next-line: only-arrow-functions
  //   const promise = new Promise((resolve, reject) => {

  //     setTimeout(() => {
  //       resolve('Promise returns after 1.5 second!');
  //       // reject('Rejected!');
  //     }, 5500);
  //   });
  //   return promise;
  // }
  public editUser(id: string){
    // tslint:disable-next-line: deprecation
    event.stopImmediatePropagation();
    this.router.navigate(['menu/edit-user', id]);
  }

  ngOnInit() {


  }
  select(id: string){
    this.userService.setId(id);
    this.router.navigate(['user-info', id]);

  }
  onChangeToggle(ev: CustomEvent) {
    this.themeService.enableDarkMode(ev.detail.checked);
  }
}
