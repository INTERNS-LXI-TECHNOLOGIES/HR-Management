import { Component, OnInit } from '@angular/core';
import { AppraisalService } from 'src/app/appraisal.service';

@Component({
  selector: 'app-employee-appraisal',
  templateUrl: './employee-appraisal.page.html',
  styleUrls: ['./employee-appraisal.page.scss'],
})
export class EmployeeAppraisalPage implements OnInit {
  appraisalList;

  constructor(private appService: AppraisalService) { }

  ngOnInit() {
    this.appraisalList = this.appService.getAppraisalList();
  }

}
