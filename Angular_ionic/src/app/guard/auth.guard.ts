import { AuthServerProvider } from './../services/auth/auth-jwt.service';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AlertController } from '@ionic/angular';
import { AccountService } from 'src/app/services/auth/account.service';
import { Account } from 'src/model/account.model';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  account: Account;
  constructor(private router: Router,
              private auth: AuthServerProvider,
              private alertCtrl: AlertController,
              private accountService: AccountService,
              private translate: TranslateService){}
  canActivate(route: ActivatedRouteSnapshot){

    // const expectedRole = route.data.role;
    // console.log('expected: ', expectedRole);
    return this.accountService.identity().then((account) => {
      if (account === null) {
        this.showAlert();
        return this.router.parseUrl('/login');
      } else {
        this.account = account;
        if ((account.authorities) == ('ROLE_ADMIN'))
        {return true;}
        else{
          this.showAlert();
          return this.router.parseUrl('/login');
        }
      }
    });
  }
  async showAlert()
  {
    let alert = await this.alertCtrl.create({
      header: this.translate.instant('AUTH-ALERT.header'),
      message: this.translate.instant('AUTH-ALERT.message'),
      buttons: [this.translate.instant('ALERT.OK')]
    });
    alert.present();
  }
}
