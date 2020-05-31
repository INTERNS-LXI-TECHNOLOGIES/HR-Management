import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/User';

@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.page.html',
  styleUrls: ['./adduser.page.scss'],
})
export class AdduserPage implements OnInit {
  model:userViewModel={
    name:'',
    email:'',
    feedback:''

  }
  user: Object;
  constructor(private http:HttpClient) { }

  ngOnInit() {
  }
  sendFeedback():void{
    
    let Url:string = "http://localhost:8080/api/addUser";
    alert(this.model.name);
    this.http.post(Url,this.model).subscribe(user =>this.user=user)
      

    

  }
}
export interface userViewModel{

  name:string;
  email:string;
  feedback:string;

}
