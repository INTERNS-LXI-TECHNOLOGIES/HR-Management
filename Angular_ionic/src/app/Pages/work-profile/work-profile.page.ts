import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { NavParams } from '@ionic/angular';
import { AppraisalService } from 'src/app/appraisal.service';

@Component({
  selector: 'app-work-profile',
  templateUrl: './work-profile.page.html',
  styleUrls: ['./work-profile.page.scss'],
})
export class WorkProfilePage implements OnInit {
 
  status: any;
   id;
   start:'no data';
   end: 'no data';
  constructor(private route: ActivatedRoute,private router: Router,
    private appService: AppraisalService,private userService: UserService)
    {}

  ngOnInit() {

    this.route.params.subscribe(params => {
    this.id = params['id'];
    this.start = params['start'];
      this.end = params['end'];
    this.userService.getStatus('http://localhost:8080/api/appraisal-controller-resource/status/' + this.id)
                               .subscribe(status => this.status = status);
                          
    });
  }
  sortDetails()
  {
    console.log("work profile test; date start ="+this.start+" date end = "+this.end+"user id ="+this.id)

   
    this.userService.getBydate('http://localhost:8080/api/appraisal-controller-resource/sortBydate/' 
    + this.id+ '/' + this.start +'/' +this.end)
    .subscribe(status => this.status = status);
    
  }

}
