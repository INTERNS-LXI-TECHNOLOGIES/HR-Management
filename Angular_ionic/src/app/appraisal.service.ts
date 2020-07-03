import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { UserExtraResourceService } from './../app/api/services';
import { userViewModel } from 'src/model/User';
import { TranslateService } from '@ngx-translate/core';



@Injectable({
  providedIn: 'root'
})
export class AppraisalService {
  constructor(private  http: HttpClient, private router: Router,
              private alert: AlertController,
              private userExtraCntl: UserExtraResourceService,
              private translate: TranslateService) { }
  baseUrl = 'http://localhost:8080';
  private counter = 0;

  getAppraisalList() {
      return this.http.get(this.baseUrl + '/api/user-data-beans').pipe(map(data => {
      console.log('users ::' + data);
      return data; }));
  }
  getAppraisalListBetweenDates(first: string , end: string){
    // tslint:disable-next-line: max-line-length
    return this.http.get('http://localhost:8080/api/appraisal-controller-resource/getReportBetweenTwoDate/' + first + '/' + end).pipe(map(data => {
      console.log('users ::' + data);
      return data; }));
   }


  public  getString(url: string): Observable<string>
  {
    return this.http.get(url, {responseType: 'text'}).pipe(map(data => {
      console.log('accessing data from ' + url + ' is' + data);
      return data; }));
  }
  public  getUsers(urlvalue: string)
  {
    return  this.http.get<userViewModel>(urlvalue).pipe(map(data => {
      console.log('users ::' + data);
      return data; }));
  }
  async deleteUser(id: string)
  {
    // const url: string = 'http://localhost:8080/api/user-extras/' + id;
    this.userExtraCntl.deleteUserExtraUsingDELETE(id).subscribe( data => {
      console.log('Confirm: user deletion');
      this.router.navigate(['/menu/home']);
    });
  }

}
