import { AuthServerProvider } from './../services/auth/auth-jwt.service';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AlertController } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router,
              private auth: AuthServerProvider,
              private alertCtrl: AlertController){}
  canActivate(route: ActivatedRouteSnapshot){

    // const expectedRole = route.data.role;
    // console.log('expected: ', expectedRole);
    return this.auth.users.pipe(
      take(1),
      map(users => {
        console.log('log: ', users);
        if (users){
          let role = users['role'];
          if(role == 'ROLE_ADMIN'){
            return true;
          }
          else{
            this.showAlert();
            return this.router.parseUrl('/login');
          }
        }
        else{
          this.showAlert();
          return this.router.parseUrl('/login');
        }
      })
    )
  }
  async showAlert()
  {
    let alert = await this.alertCtrl.create({
      header: 'Unauthorized',
      message: ' you are not authorized to visit that page',
      buttons: ['ok']
    });
    alert.present();
  }
}
