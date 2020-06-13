import { UserService } from 'src/app/service/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-appraisal-details',
  templateUrl: './appraisal-details.page.html',
  styleUrls: ['./appraisal-details.page.scss'],
})
export class AppraisalDetailsPage implements OnInit {
  appraisal;
  id;
  user;
  image;
  start = ' ';
  end = '';
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.id = this.userService.getId();
    this.userService.getAppraisal('http://localhost:8080/api/appraisal-controller-resource/appraisal/' + this.id)
                                .subscribe(data => this.appraisal = data);
    this.userService.getUser('http://localhost:8080/api/appraisal-controller-resource/user-extras/' + this.id)
                                .subscribe(user => this.user = user);
    this.userService.getImage('http://localhost:8080/api/appraisal-controller-resource/image/' + this.id)
                                .subscribe(image => {this.image = image; },
                                  (error: any) => {console.log(error); } );
  }
  getPdf(id)
  {
    // this.userService.getPdf('http://localhost:8080/api/appraisal-controller-resource/getPdf/' + this.id)
    //                             .subscribe(user => this.user = user);
    alert('april fool/...!');

  }

}
