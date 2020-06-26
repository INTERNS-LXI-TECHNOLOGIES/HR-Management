import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { userViewModel } from '../../model/User';
import { AlertController } from '@ionic/angular';
import { AppraisalControllerResourceService } from './../../api/services';


@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.page.html',
  styleUrls: ['./adduser.page.scss'],
})
export class AdduserPage implements OnInit {
  model: AppraisalControllerResourceService.AddUserUsingPOSTParams = {
    firstName: '',
    lastName: '',
    company: '',
    email: '',
    position: '',
    authorities: '',
    joiningDate: '',
    dob: '',
    image: '',
    login: '',
    password: ''
  };
  user;
  file: File;
  files: FileList;
  constructor(private http: HttpClient,
              private router: Router,
              private alert: AlertController,
              private appCntl: AppraisalControllerResourceService, ) { }

  ngOnInit() {
  }
  sendFeedback(): void{
    const url = 'http://localhost:8080/api/appraisal-controller-resource/addUser';
    // const tempToken = this.getToken();
    const headers = {
      enctype: 'multipart/form-data;',
      Accept: 'plain/text',
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
    this.appCntl.addUserUsingPOST( {
      position : this.model.position,
      password : this.model.password,
      login : this.model.login,
      lastName : this.model.lastName,
      joiningDate : this.model.joiningDate,
      image : this.model.image,
      firstName : this.model.firstName,
      email : this.model.email,
      dob : this.model.dob,
      company : this.model.company,
      authorities : this.model.authorities
    })
    // this.http.post(url,  formData)
    .subscribe(async data => {
      this.user = data;
      if (this.user === true)
      {
        alert('login ID is already used' );
      }
      else{
        const alertPrompt = await this.alert.create({
          cssClass: 'my-custom-class',
          header: 'Success',
          subHeader: 'Creation',
          message: ' new User created successfully.',
          buttons: ['OK']
        });
        await alertPrompt.present();
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
      alert('something went wrong..!' );
    });
  }
  setFile(event)
  {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    this.model.image = reader.result;
    // tslint:disable-next-line: only-arrow-functions
    reader.onload = function() {
     // me.modelvalue = reader.result;
     console.log(reader.result);
   };
    // tslint:disable-next-line: only-arrow-functions
    reader.onerror = function(error) {
     console.log('Error: ', error);
   };
    // this.model.image = event.target.files[0];
  }
}

