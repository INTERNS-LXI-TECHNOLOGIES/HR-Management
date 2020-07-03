import { AuthServerProvider } from './../../services/auth/auth-jwt.service';
import { Router } from '@angular/router';
import { Account } from 'src/model/account.model';
import { Component, OnInit } from '@angular/core';
import { NavController, ToastController } from '@ionic/angular';
import { LoginService } from 'src/app/services/login/login.service';
import { AccountService } from 'src/app/services/auth/account.service';
import { LanguagePopoverPage } from './../language-popover/language-popover.page';
import { PopoverController } from '@ionic/angular';
@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  // The account fields for the login form.
  account: Account;
  user: { username: string; password: string} = {
    username: '',
    password: '',
  };

  // Our translated text strings
  private loginErrorString: string;
  
  constructor(
    public loginService: LoginService,
    public toastController: ToastController,
    public navController: NavController,
    private accountService: AccountService,
    private router: Router,
    private authServerProvider: AuthServerProvider,
    private popoverCtrl: PopoverController
  ) {}

  ngOnInit() {}

  doLogin() {
    this.loginService.login(this.user).then(
      () => {
        // this.loginService.getUser(this.account.username)
        this.accountService.identity().then((account) => {
          if (account === null) {
            this.goBackToHomePage();
          } else {
            this.account = account;
            this.getRole(this.account);
            if ((account.authorities) == ('ROLE_ADMIN'))
            {this.navController.navigateRoot('/menu/home'); }
            else if ((account.authorities) == ('ROLE_USER')){
            this.router.navigate(['/user-info', (account.id)]);
            }
          }
        });
      },
      async (err) => {
        // Unable to log in
        this.user.password = '';
        const toast = await this.toastController.create({
          message: this.loginErrorString,
          duration: 3000,
          position: 'top',
        });
        toast.present();
      }
    );
  }
  private goBackToHomePage(): void {
    this.navController.navigateBack('/login');
  }
  getRole(account)
  {
    this.authServerProvider.getRole(account).subscribe( user => {
      console.log('after login', user);
    } )
  }
  async openLanguagePopover(ev) {
    const popover = await this.popoverCtrl.create({
      component: LanguagePopoverPage,
      event: ev
    });
    await popover.present();
  }
}
