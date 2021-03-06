import { LoginService } from './../../services/login/login.service';
import { AuthServerProvider } from './../../services/auth/auth-jwt.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { PopoverComponentPage } from '../popover-component/popover-component.page';
import { PopoverController } from '@ionic/angular';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.page.html',
  styleUrls: ['./user-info.page.scss'],
})
export class UserInfoPage implements OnInit {
  public id;
  image;
  user;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UserService,
              private loginService: LoginService,
              private popoverCtrl: PopoverController){}
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.userService.getUser('http://localhost:8080/api/appraisal-controller-resource/user-extras/' + this.id)
                                .subscribe(user => this.user = user);
      this.userService.getImage('http://localhost:8080/api/appraisal-controller-resource/image/' + this.id)
                                .subscribe(image => {this.image = image; },
                                  (error: any) => {console.log(error); } );
      console.log(this.image);
      this.userService.setId(this.id);
    });
  }
  async openPopover(ev) {
    const popover = await this.popoverCtrl.create({
      component: PopoverComponentPage,
      event: ev
    });
    await popover.present();
  }
}
