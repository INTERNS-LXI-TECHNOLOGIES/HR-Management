import { Router } from '@angular/router';
import { Account } from 'src/model/account.model';
import { Component, OnInit } from '@angular/core';
import { NavController, ToastController } from '@ionic/angular';
import { LoginService } from 'src/app/services/login/login.service';
import { AccountService } from 'src/app/services/auth/account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  // The account fields for the login form.
  account: Account;
  user: { username: string; password: string; rememberMe: boolean } = {
    username: '',
    password: '',
    rememberMe: false,
  };

  // Our translated text strings
  private loginErrorString: string;

  constructor(
    public loginService: LoginService,
    public toastController: ToastController,
    public navController: NavController,
    private accountService: AccountService,
    private router: Router,
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
            if((account.authorities)==("ROLE_ADMIN"))
            {this.navController.navigateRoot('/menu/home');}
            else{
            // this.navController.navigateRoot('/menu/user-info/',(account.id));
            this.router.navigate(['/menu/user-info', (account.id)]);
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
}
