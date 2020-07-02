import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { AppraisalControllerResourceService} from './../../api/services';
import { userViewModel } from 'src/model/User';
import { Base64 } from '@ionic-native/base64/ngx';


@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.page.html',
  styleUrls: ['./adduser.page.scss'],
})
// tslint:disable-next-line: component-class-suffix
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
    // const formData = new FormData();
    // // const data = JSON.stringify(this.model);
    // formData.append( 'firstName', this.model.firstName );
    // formData.append( 'lastName', this.model.lastName );
    // formData.append( 'company', this.model.company );
    // formData.append( 'email', this.model.email );
    // formData.append( 'position', this.model.position );
    // formData.append( 'authorities', this.model.authorities );
    // formData.append( 'joiningDate', this.model.joiningDate );
    // formData.append( 'dob', this.model.dob );
    // // formData.append( 'image', this.model.image );
    // formData.append( 'login', this.model.login );
    // formData.append( 'password', this.model.password );
    // // formData.append( 'firstName', this.model.firstName );
    // // alert(this.model.name);
    // console.log('formData: ', formData.getAll('data'));
    this.appCntl.addUserUsingPOST(
      this.model
    )
    // this.http.post(url,  formData)
    .subscribe(async data => {
      this.user = data;
      if (this.user === true)
      {
          const alertPro = await this.alert.create({
            cssClass: 'my-custom-class',
            header: 'Error',
            subHeader: 'Unsuccessful',
            message: 'Login ID is already user, try another..!',
            buttons: ['okay']
          });
          await alertPro.present();
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
    async err => {
      const alertPro = await this.alert.create({
        cssClass: 'my-custom-class',
        header: 'Error',
        subHeader: 'Unsuccessful',
        message: 'Something went Wrong, try again..!',
        buttons: ['okay']
      });
      await alertPro.present();
    });
  }
  setFile(event)
  {
    const file = event.target.files[0];
    console.log(file);
    let blob = null;
    // let stringData = btoa(file);
    const reader = new FileReader();
    reader.readAsDataURL(file);
    // tslint:disable-next-line: only-arrow-functions
    reader.onload = function() {
      // blob = new Blob([stringData], {type: 'image/jpeg'});
      // me.modelvalue = reader.result;
      console.log(file);
      // console.log(blob);
      
    };
    setTimeout(() => {
      let stringData = reader.result.toString();

      console.log(stringData);
  
      // this.model.image = stringData;
    }, 2000);
    // tslint:disable-next-line: only-arrow-functions
    reader.onerror = function(error) {
     console.log('Error: ', error);
   };
  }

}

