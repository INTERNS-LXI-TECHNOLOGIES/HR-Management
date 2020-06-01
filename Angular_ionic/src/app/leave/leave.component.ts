import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { onErrorResumeNext } from 'rxjs';
import { error } from '@angular/compiler/src/util';
import {Leave} from 'src/app/model/Leave';
@Component({
  selector: 'app-leave',
  templateUrl: './pages/leave/leave.module',
  styleUrls: ['./pages/leave/leave.scss'],
})
export class LeaveComponent implements OnInit {
  leave : Leave[] = [];
  constructor(private http:HttpClient) { }

  ngOnInit() {
    this.getLeave();
  }

  public getLeave()
  {
    let url = 'http://localhost:8080/api/leaves/';
    this.http.get<Leave>(url).subscribe((res)=>{
      console.log(res);}
       );

  }
}
