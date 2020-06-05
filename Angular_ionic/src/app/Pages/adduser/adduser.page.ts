import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { userViewModel } from '../../model/User';

@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.page.html',
  styleUrls: ['./adduser.page.scss'],
})
export class AdduserPage implements OnInit {
  model: userViewModel = {
    firstName: "",
    lastName: "",
    company: "",
    email: "",
    position: "",
    authorities: "",  
    joiningDate: "",
    dob: "",
    image: null,
    login: "",
    password: ""

  }
  user;
  file: File;
  files: FileList;
  constructor(private http: HttpClient,
              private router: Router ) { }

  ngOnInit() {
  }
  sendFeedback(): void{
    const url = 'http://localhost:8080/api/appraisal-controller-resource/addUser';
    let httpOptions = {
      headers: new HttpHeaders({
          'enctype': 'multipart/form-data; boundary=----WebKitFormBoundaryuL67FWkv1CA'
      })
  };
    // alert(this.model.name);
    this.http.post(url,  this.model, httpOptions).subscribe(data => {
      this.user = data;
      if (this.user === true)
      {
        alert('login ID is already used' );
      }
      else{
        alert('user added');
      //   if (this.file.size > 20)
      // {
      //   this.http.put(url, this.file, httpOptions).subscribe(data => {
      //     this.user = data;
      //   });

      // }
        this.router.navigateByUrl('/home');

      }

    },
    err => {
      alert('something went wrong..!');
    });
    
  }
  
  setFile(event: Event)
  {
    this.model.image = (<HTMLInputElement> event.target).files[0];
    console.log(this.model.image.type);
  }
}

