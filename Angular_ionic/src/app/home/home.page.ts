import { Component, OnInit } from '@angular/core';
import { AppraisalService } from '../appraisal.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  
  users:any=this.appservice.getUsers('http://localhost:8080/api/users/');
  constructor(private appservice: AppraisalService){

  }
  // constructor(public navCtrl: NavController, public dataprovider: DataProviderService ) { }

  // getValue()
  // {
  //   this.dataprovider.getLocalData();
  // }
  ngOnInit() {
  }

}
