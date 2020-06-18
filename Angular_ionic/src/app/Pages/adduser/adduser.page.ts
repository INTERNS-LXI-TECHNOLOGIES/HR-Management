import { User } from './../../auth/user';
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
    id: '',
    firstName: '',
    lastName: '',
    company: '',
    email: '',
    position: '',
    authorities: '',
    joiningDate: '',
    dob: '',
    image: null,
    login: '',
    password: ''
  };
  user;
  file: File;
  files: FileList;
  constructor(private http: HttpClient,
              private router: Router ) { }

  ngOnInit() {
  }
  sendFeedback(): void{
    const url = 'http://localhost:8080/api/appraisal-controller-resource/addUser';
    // const tempToken = this.getToken();
    const headers = {
      'enctype': 'multipart/form-data;',
      'Accept': 'plain/text',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT',
      'Access-Control-Allow-Headers': 'Authorization, Origin, Content-Type, X-CSRF-Token',
    };
    const formData = new FormData();
    // const data = JSON.stringify(this.model);
    formData.append( 'firstName', this.model.firstName );
    formData.append( 'lastName', this.model.lastName );
    formData.append( 'company', this.model.company );
    formData.append( 'email', this.model.email );
    formData.append( 'position', this.model.position );
    formData.append( 'authorities', this.model.authorities );
    formData.append( 'joiningDate', this.model.joiningDate );
    formData.append( 'dob', this.model.dob );
    formData.append( 'image', this.model.image );
    formData.append( 'login', this.model.login );
    formData.append( 'password', this.model.password );
    // formData.append( 'firstName', this.model.firstName );
    // alert(this.model.name);
    console.log('formData: ', formData.getAll('data'));
    this.http.post(url,  formData).subscribe(data => {
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
        this.router.navigateByUrl('/menu/home');
      }

    },
    err => {
      alert('something went wrong..!' + this.model.image.type+ ' nn  ' + this.model.company );
    });
    this.sign();
  }
  setFile(event)
  {
    this.model.image = event.target.files[0];
  }
  sign()
  {
    const formData = new FormData();
    formData.append( 'login', this.model.login );
    formData.append( 'password', this.model.password );
  }

}

