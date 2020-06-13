import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { userViewModel } from './model/User';

@Injectable({
  providedIn: 'root'
})
export class AppraisalService {
  private counter = 0;

  baseUrl:string = "http://localhost:8080";

  public  getString(url:string): Observable<string>
  {
    return this.http.get(url, {responseType: 'text'}).pipe(map(data => {
      console.log('accessing data from '+url+' is'+data);
        return data;}));
  }
  public  getUsers(urlvalue:string)
  {
    return  this.http.get<userViewModel>(urlvalue).pipe(map(data => {
      console.log('users ::'+data);
        return data;}));
  }
  deleteUser(id:string)
  {
    this.counter += 1;
    let url:string = "http://localhost:8080/api/user-extras/"+id;
    this.http.delete(url).subscribe(data=>{
      alert("User removed Succesfully..!" );
      this.router.navigate(['/home']);
    },
    err=> {
      alert("something went wromg..!" ); 
    });
    
  }
  constructor(private  http : HttpClient, private router:Router) { }
    
}