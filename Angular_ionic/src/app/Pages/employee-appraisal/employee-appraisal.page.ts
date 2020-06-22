import { UserService } from './../../service/user.service';
import { Component, OnInit } from '@angular/core';
import { AppraisalService } from 'src/app/appraisal.service';
import { userViewModel } from 'src/app/model/User';
import { Observable } from 'rxjs';
import { File } from '@ionic-native/file/ngx';
import { FileTransfer } from '@ionic-native/file-transfer/ngx';
import { FileOpener } from '@ionic-native/file-opener/ngx';

@Component({
  selector: 'app-employee-appraisal',
  templateUrl: './employee-appraisal.page.html',
  styleUrls: ['./employee-appraisal.page.scss'],
})
// tslint:disable-next-line: component-class-suffix
export class EmployeeAppraisalPage implements OnInit {
  appraisalList;
  sort;
  start = 'no data';
  end = 'no data';
  month: string;
  users: Observable <userViewModel> = this.appService.getUsers('http://localhost:8080/api/users/');
  // tslint:disable-next-line: align
  // private file: FileOpener<typeof FileOpener | null (null) >;

  constructor(private appService: AppraisalService,
              // private transfer: FileTransfer,
              public fileOpener: FileOpener,
              private file: File,
              private userService: UserService,
              ) { }

  ngOnInit() {
    this.appraisalList = this.appService.getAppraisalList();
    this.sort = true;

  }
  getPdf(){
    // alert('data' + this.start + this.end );
      // tslint:disable-next-line: max-line-length
    this.userService.getReport('http://localhost:8080/api/appraisal-controller-resource/report/' + this.sort + '/' + this.start + '/' + this.end)
                                .subscribe(data =>
      {
        const file = new Blob([data], {type: 'application/pdf'});
        const fileUrl = URL.createObjectURL(file);
        window.open(fileUrl);
        // const res = this.file.createFile(this.file.externalCacheDirectory, 'appraisal Report.pdf', true);
        // if (res !== undefined)
        // {
        //   res.then(() => {
        //     console.log('file created');

        //     this.file.writeFile(this.file.externalCacheDirectory, 'appraisal Report.pdf', file, {
        //       replace: true})
        //       .then(value => {
        //         console.log('file write done');
        //         this.fileOpener
        //           .showOpenWithDialog(
        //             this.file.externalCacheDirectory + 'appraisal Report.pdf',
        //             'application/pdf'
        //           )
        //           .then(() => console.log('File is opened'))
        //           .catch(e => console.log('Error opening file', e));
        //         // this.documentViewer.viewDocument(this.file.externalCacheDirectory + 'items.pdf', 'application/pdf',
        //         // {print: {enabled: true}, openWith: {enabled: true}});

        //       });
        //   });
        // }
      });




// this.fileOpener.open(fileUrl, 'application/pdf')
//         .then(() => console.log('File is opened'))
//         .catch(e => console.log('Error opening file', e));
//       })

  }
sortDetails(){
    this.appraisalList = this.appService.getAppraisalListBetweenDates(this.start, this.end);
    this.sort = false;
  }

}
