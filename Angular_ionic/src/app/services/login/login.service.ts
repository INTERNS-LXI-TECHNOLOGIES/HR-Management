import { Injectable } from '@angular/core';
import { AccountService } from '../auth/account.service';
import { AuthServerProvider } from '../auth/auth-jwt.service';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(
    private accountService: AccountService,
    private authServerProvider: AuthServerProvider,
  ) {}

  login(credentials, callback?) {
    const cb = callback || function () {};
    return new Promise((resolve, reject) => {
      this.authServerProvider.login(credentials).subscribe(
        (data) => {
          this.accountService.identity(true).then((account) => {
            // After the login the language will be changed to
            // the language selected by the user during his registration
            resolve(data);
          });
          return cb();
        },
        (err) => {
          this.logout();
          reject(err);
          return cb(err);
        }
      );
    });
  }

  loginWithToken(jwt) {
    return this.authServerProvider.loginWithToken(jwt);
  }

  logout() {
    this.authServerProvider.logout().subscribe();
    this.accountService.authenticate(null);
  }
}
