import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.page.html',
  styleUrls: ['./user-info.page.scss'],
})
export class UserInfoPage implements OnInit {
  
  id: any;
  constructor(private route: ActivatedRoute){}
  ngOnInit() {

    this.route.params.subscribe(params => {
      this.id= params['id'];
    });
  }

}
