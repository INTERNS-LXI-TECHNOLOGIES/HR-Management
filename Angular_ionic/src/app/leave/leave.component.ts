import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { onErrorResumeNext } from 'rxjs';
import { error } from '@angular/compiler/src/util';

@Component({
  selector: 'app-leave',
  templateUrl: './pages/leave/leave.module',
  styleUrls: ['./pages/leave/leave.scss'],
})
export class LeaveComponent implements OnInit {

  constructor(private http:HttpClient) { }

  ngOnInit() {}

  public getLeave()
  {
    let url = 'http://localhost:8080/api/leaves/';
    this.http.get(url).subscribe(   
      error , (err: any) =>{
        alert("an error has occured;")
      }
    );
  }
}
