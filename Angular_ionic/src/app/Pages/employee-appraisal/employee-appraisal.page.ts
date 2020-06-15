import { UserService } from './../../service/user.service';
import { Component, OnInit } from '@angular/core';
import { AppraisalService } from 'src/app/appraisal.service';
import { userViewModel } from 'src/app/model/User';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-employee-appraisal',
  templateUrl: './employee-appraisal.page.html',
  styleUrls: ['./employee-appraisal.page.scss'],
})
export class EmployeeAppraisalPage implements OnInit {
  appraisalList;
  users: Observable <userViewModel> = this.appService.getUsers('http://localhost:8080/api/users/');

  constructor(private appService: AppraisalService,
              private userService: UserService) { }

  ngOnInit() {
    this.appraisalList = this.appService.getAppraisalList();
  }
  getPdf(){
    this.userService.getReport('http://localhost:8080/api/appraisal-controller-resource/report/')
                                .subscribe(data =>
      {
        const file = new Blob([data], {type: 'application/pdf'});
        const fileUrl = URL.createObjectURL(file);
        window.open(fileUrl);
      });

  }

}
