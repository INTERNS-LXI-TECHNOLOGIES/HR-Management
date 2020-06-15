import { HttpClient } from '@angular/common/http';
import { userViewModel } from 'src/app/model/User';
import { UserService } from './../../service/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AppraisalService } from 'src/app/appraisal.service';
import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/compiler/src/core';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.page.html',
  styleUrls: ['./edit-user.page.scss'],
})
export class EditUserPage implements OnInit {

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private httpClient: HttpClient,
              private router: Router,
    ) { }
  id;
  image: File;
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
  ngOnInit() {
    this.route.params.subscribe(params => {
      // tslint:disable-next-line: no-string-literal
      this.id = params['id']; });
    this.userService.getUser('http://localhost:8080/api/appraisal-controller-resource/user-extras/' + this.id)
    .subscribe(model => this.model = model);
  }
  sendFeedback(){
    const formData = new FormData();
    // const data = JSON.stringify(this.model);
    formData.append( 'id', this.id );
    formData.append( 'firstName', this.model.firstName );
    formData.append( 'lastName', this.model.lastName );
    formData.append( 'company', this.model.company );
    formData.append( 'email', this.model.email );
    formData.append( 'position', this.model.position );
    formData.append( 'joiningDate', this.model.joiningDate );
    formData.append( 'dob', this.model.dob );
    if (this.image == null){}
    else{
      //  alert('image added');
       formData.append( 'image', this.image );
     }
    this.userService.editUser(formData).subscribe(
      data => {
       alert('user details modified');
       this.userService.setId(this.id);
       this.router.navigate(['/menu/home/user-info/', this.id]);
      },
      err => {
       alert('error while modifying user details');
      }
    );
  }
  setFile(event){
    this.image = event.target.files[0];
  }

}