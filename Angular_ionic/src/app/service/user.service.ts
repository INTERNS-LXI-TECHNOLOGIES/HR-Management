import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl:string ="http://localhost:8080"
  getUser(url:string) 
  {
    return  this.http.get(url);
    // .pipe(map(data => {
    //   console.log('users ::'+data);
    //     return data;})); 
  }
  constructor(private  http : HttpClient) { }
}
