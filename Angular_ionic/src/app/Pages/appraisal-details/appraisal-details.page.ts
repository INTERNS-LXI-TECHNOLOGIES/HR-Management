import { UserService } from 'src/app/service/user.service';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { AlertController } from '@ionic/angular';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-appraisal-details',
  templateUrl: './appraisal-details.page.html',
  styleUrls: ['./appraisal-details.page.scss'],
})
export class AppraisalDetailsPage implements OnInit {
  appraisal;
  id;
  user;
  byteArray;
  image;
  start = 'now ';
  today = new Date();
  end = this.today.toISOString();
  sort = true;
  constructor(private userService: UserService,
              private alert: AlertController,
              private translate: TranslateService) { }

  ngOnInit() {
    this.id = this.userService.getId();
    this.userService.getAppraisal('http://localhost:8080/api/appraisal-controller-resource/appraisal/' + this.id)
                                .subscribe(datas => this.appraisal = datas);
    this.userService.getUser('http://localhost:8080/api/appraisal-controller-resource/user-extras/' + this.id)
                                .subscribe(user => this.user = user);
    this.userService.getImage('http://localhost:8080/api/appraisal-controller-resource/image/' + this.id)
                                .subscribe(image => {this.image = image;
                                                     this.start = this.user.joiningDate;
                                                     this.end = this.today.toISOString(); },
                                  (error: any) => {console.log(error); } );

  }
  getPdf(id)
  {

    // tslint:disable-next-line: max-line-length
    this.userService.getPdf('http://localhost:8080/api/appraisal-controller-resource/getPdf/' + this.id + '/' + this.start + '/' + this.end + '/' + this.user.joiningDate + '/' + this.sort )
                                .subscribe(data =>
      {
        const file = new Blob([data], {type: 'application/pdf'});
        const fileUrl = URL.createObjectURL(file);
        window.open(fileUrl);
      });
  }
  sortDetails()
  {
    // tslint:disable-next-line: max-line-length
    this.userService.sortAppraisal('http://localhost:8080/api/appraisal-controller-resource/sortAppraisal/' + this.id + '/' + this.start + '/' + this.end)
                                .subscribe(data => {this.appraisal = data;
                                                    this.sort = false; },
    async err => {
    const alertPrompt = await this.alert.create({
      cssClass: 'my-custom-class',
      message:  this.translate.instant('APPRAISAL-ALERT.message'),
      buttons: [this.translate.instant('ALERT.OK')]
    });
    await alertPrompt.present();
  });
  }

}
