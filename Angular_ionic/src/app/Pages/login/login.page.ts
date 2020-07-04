import { AuthServerProvider } from './../../services/auth/auth-jwt.service';
import { Router } from '@angular/router';
import { Account } from 'src/model/account.model';
import { Component, OnInit } from '@angular/core';
import { NavController, ToastController, AlertController } from '@ionic/angular';
import { LoginService } from 'src/app/services/login/login.service';
import { AccountService } from 'src/app/services/auth/account.service';
import { LanguagePopoverPage } from './../language-popover/language-popover.page';
import { PopoverController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';
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
  constructor(
    public loginService: LoginService,
    public toastController: ToastController,
    public navController: NavController,
    private accountService: AccountService,
    private router: Router,
    private authServerProvider: AuthServerProvider,
    private popoverCtrl: PopoverController,
    public translate: TranslateService,
    private alert: AlertController
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
        const alertPrompt = await this.alert.create({
          cssClass: 'my-custom-class',
          header: this.translate.instant('LOGIN-ALERT.header'),
          message: this.translate.instant('LOGIN-ALERT.message'),
          buttons: [this.translate.instant('ALERT.OK')]
        });
        await alertPrompt.present();
      });
  }
  private async goBackToHomePage(): Promise<any>
  {
    const alertPrompt = await this.alert.create({
      cssClass: 'my-custom-class',
      header: this.translate.instant('LOGIN-ALERT.header'),
      message: this.translate.instant('LOGIN-ALERT.message'),
      buttons: [this.translate.instant('ALERT.OK')]
    });
    await alertPrompt.present();
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
