import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/Rx';

import { map } from 'rxjs/operators';
import  'rxjs/add/operator/catch';
import  'rxjs/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class AppraisalService {

  baseUrl:string = "http://localhost:8080";

  constructor(private  http : HttpClient) { }

  public  getString(): Observable<string>
  {

    return this.http.get(this.baseUrl + '/api/appraisal-controller-resource/',{responseType: 'text'}).pipe(map(data => {
      console.log('raw ::'+data);
        return data;}));
  }
  public  getUsers(): Observable<string[]> 
  {

    return  this.http.get(this.baseUrl + '/api/users/').pipe(map(data => {
      console.log('users ::'+data);
        return data;}))
    .catch((err)=>{
    console.error(err);
    });
    }
    
}
