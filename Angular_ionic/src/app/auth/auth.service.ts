import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable,  ReplaySubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Storage } from '@ionic/storage';
import { User } from './user';
import { AuthResponse } from './auth-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private readonly jwtTokenName = 'jwt_token';

  private authUser = new ReplaySubject<any>(1);

  constructor(private  httpClient: HttpClient, private  storage: Storage) {
   }
   login(values: any): Observable<string> {
    return this.httpClient.post(`http://localhost:8080/api/authenticate`, values, {responseType: 'text'})
      .pipe(tap(jwt => this.handleJwtResponse(jwt)));
  }
  private handleJwtResponse(jwt: string): string {
    localStorage.setItem(this.jwtTokenName, jwt);
    this.authUser.next(jwt);

    return jwt;
  }
}
