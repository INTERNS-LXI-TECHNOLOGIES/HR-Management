import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AppraisalService {

  baseUrl:string = "http://localhost:8080";

  public  getString(url:string): Observable<string>
  {

    return this.http.get(url,{responseType: 'text'}).pipe(map(data => {
      console.log('accessing data from '+url+' is'+data);
        return data;}));
  }
  public  getUsers(urlvalue:string)
  {
    return  this.http.get(urlvalue).pipe(map(data => {
      console.log('users ::'+data);
        return data;}));
    }
  constructor(private  http : HttpClient) { }
    
}