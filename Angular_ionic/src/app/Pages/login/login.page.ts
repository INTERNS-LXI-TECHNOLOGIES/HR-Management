import { AuthService } from './../../auth/auth.service';
import { Component, OnInit } from '@angular/core';
import {LoadingController, NavController, ToastController} from '@ionic/angular';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  constructor(private readonly navCtrl: NavController,
              private readonly loadingCtrl: LoadingController,
              private readonly authService: AuthService,
              private readonly toastCtrl: ToastController) {
  }
  ngOnInit() {
  }
  async login(value: any) {
    const loading = await this.loadingCtrl.create({
      spinner: 'bubbles',
      message: 'Logging in ...'
    });

    loading.present();

    this.authService
      .login(value)
      .pipe(finalize(() => loading.dismiss()))
      .subscribe(
        _ => {
          this.navCtrl.navigateRoot(['/menu/home'], {replaceUrl: true});
        },
        err => this.handleError(err));
  }

  async handleError(error: any) {
    let message: string;
    if (error.status && error.status === 401) {
      message = 'Login failed';
    } else {
      message = `Unexpected error: ${error.statusText}`;
    }

    const toast = await this.toastCtrl.create({
      message,
      duration: 5000,
      position: 'bottom'
    });

    toast.present();
  }

}
