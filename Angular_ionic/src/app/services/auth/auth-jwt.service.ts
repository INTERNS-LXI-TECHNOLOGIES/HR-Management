import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService, SessionStorageService } from 'ngx-webstorage';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { map , filter} from 'rxjs/operators';
import { Storage } from '@ionic/Storage';
import { ApiService } from '../api/api.service';
import { stringify } from 'querystring';

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
              private storage: Storage,
              private router: Router) {
                this.loadUser();
                // this.users = this.authState.asObservable();
                this.users = this.authState.asObservable().pipe(
                  filter(response => response)
                )
              }
  loadUser() {
    const data = this.$localStorage.retrieve(TOKEN_KEY);
    console.log('Loaded user ', data);
    if (data){
      this.authState.next(data);
      // let role = data['role'];
      // if()
    }
    else{
      this.authState.next(null);
    }
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
      // const jwt = resp.body.id_token;
      // this.storeAuthenticationToken(jwt);
      // return jwt;
      const bearerToken = resp.headers.get('Authorization');
      if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
        const jwt = bearerToken.slice(7, bearerToken.length);
        this.storeAuthenticationToken(jwt);
        return jwt;
      }
    }
  }
  loginWithToken(jwt) {
    if (jwt) {
      this.storeAuthenticationToken(jwt);
      return Promise.resolve(jwt);
    } else {
      return Promise.reject('auth-jwt-service Promise reject'); // Put appropriate error message here
    }
  }

  storeAuthenticationToken(jwt) {
      this.$localStorage.store('authenticationToken', jwt);
      this.$sessionStorage.store('authenticationToken', jwt);
      this.storage.set('authenticationToken', jwt);
  }

  logout(): Observable<any> {
    return new Observable((observer) => {
      this.$localStorage.clear('authenticationToken');
      this.$sessionStorage.clear('authenticationToken');
      this.storage.remove('authenticationToken');
      this.$sessionStorage.clear(TOKEN_KEY);
      this.authState.next(null);
      this.router.navigateByUrl('/login');
      observer.complete();
    });
  }

  getRole(account): Observable<any>{
    let users = null;
    let username = (account.login);
    if ((account.authorities) == ('ROLE_ADMIN'))
    {
      users = { username, role: 'ROLE_ADMIN'};
    }
    else{
      users = { username, role: 'ROLE_USER'};
    }
    this.authState.next(this.users);
    this.$localStorage.store(TOKEN_KEY, (users));
    // this.setValue(this.users);
    return of(users);
  }
  // setValue(users): Promise<any>
  // {
  //   return this.storage.set(TOKEN_KEY,(users));
  // }
  signout()
  {
    this.storage.set(TOKEN_KEY, null);
    this.authState.next(null);
    this.router.navigateByUrl('/login');
  }
}
