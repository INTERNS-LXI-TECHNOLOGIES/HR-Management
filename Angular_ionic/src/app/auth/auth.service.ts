import {Injectable} from '@angular/core';
import {Observable, ReplaySubject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {NavController} from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly jwtTokenName = 'jwt_token';
  details;
  private authUser = new ReplaySubject<any>(1);
  public authUserObservable = this.authUser.asObservable();

  constructor(private readonly httpClient: HttpClient,
              private readonly navCtrl: NavController) {}
  login(values: any): Observable<string> {
    return this.httpClient.post(`http://localhost:8080/api/authenticate`, values, {responseType: 'text'})
      .pipe(tap(jwt => this.handleJwtResponse(jwt)));
  }
  private handleJwtResponse(jwt: string): string {
    localStorage.setItem(this.jwtTokenName, jwt);
    this.authUser.next(jwt);
    console.log(jwt);
    return jwt;
  }
  getUser(value: any)
  {
    return this.httpClient.get('http://localhost:8080/api/getuser', value)
    .pipe(tap(details => this.details = console.log(details)) );
  }
  // hasAccess(): Promise<boolean> {
  //   const jwt = localStorage.getItem(this.jwtTokenName);

  //   if (jwt && !this.jwtHelper.isTokenExpired(jwt)) {

  //     return new Promise((resolve, _) => {

  //       this.httpClient.get(`http://localhost:8080/api/auth`)
  //         .subscribe(() => {
  //             this.authUser.next(jwt);
  //             resolve(true);
  //           },
  //           err => {
  //             this.logout();
  //             resolve(false);
  //           });
  //     });

  //     // OR
  //     // this.authUser.next(jwt);
  //     // Promise.resolve(true);
  //   } else {
  //     this.logout();
  //     return Promise.resolve(false);
  //   }
  // }
  logout() {
    localStorage.removeItem(this.jwtTokenName);
    this.authUser.next(null);
    this.navCtrl.navigateRoot('login', {replaceUrl: true});
  }
}
