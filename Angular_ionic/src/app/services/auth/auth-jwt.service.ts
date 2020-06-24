import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService, SessionStorageService } from 'ngx-webstorage';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Storage } from '@ionic/Storage';
import { ApiService } from '../api/api.service';

const TOKEN_KEY = 'user-access-token';
@Injectable({
  providedIn: 'root',
})
export class AuthServerProvider {
 
  users: Observable<any>;
  private authState = new BehaviorSubject(null);

  constructor(private http: HttpClient,
              private $localStorage: LocalStorageService,
              private $sessionStorage: SessionStorageService,
              private storage: Storage) { 
                this.users = this.authState.asObservable();
              }

  getToken() {
    return this.$localStorage.retrieve('authenticationToken') || this.$sessionStorage.retrieve('authenticationToken');
  }

  login(credentials): Observable<any> {
    const data = {
      username: credentials.username,
      password: credentials.password,
      rememberMe: credentials.rememberMe,
    };
  
    return this.http.post(ApiService.API_URL + '/authenticate', data, { observe: 'response' }).pipe(map(authenticateSuccess.bind(this)));

    function authenticateSuccess(resp) {
      const bearerToken = resp.headers.get('Authorization');
      if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
        const jwt = bearerToken.slice(7, bearerToken.length);
        this.storeAuthenticationToken(jwt, credentials.rememberMe);
        return jwt;
      }
    }
  }

  loginWithToken(jwt, rememberMe) {
    if (jwt) {
      this.storeAuthenticationToken(jwt, rememberMe);
      return Promise.resolve(jwt);
    } else {
      return Promise.reject('auth-jwt-service Promise reject'); // Put appropriate error message here
    }
  }

  storeAuthenticationToken(jwt, rememberMe) {
    if (rememberMe) {
      this.$localStorage.store('authenticationToken', jwt);
    } else {
      this.$sessionStorage.store('authenticationToken', jwt);
    }
  }

  logout(): Observable<any> {
    return new Observable((observer) => {
      this.$localStorage.clear('authenticationToken');
      this.$sessionStorage.clear('authenticationToken');
      observer.complete();
    });
  }
  getRole(account): Observable<any>{
    let users = null;
    let username = (account.login);
    let auth;
    if ((account.authorities) == ('ROLE_ADMIN'))
    {
      auth = 'ROLE_ADMIN';
    }
    else{
      auth = 'ROLE_USER';
    }
    users = { username, role: auth};
    this.authState.next(this.users);
    this.storage.set(TOKEN_KEY, users);
    return of(users);
  }
}
