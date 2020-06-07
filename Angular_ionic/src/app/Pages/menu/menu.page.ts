import { Component, OnInit } from '@angular/core';
import { Router, RouterEvent } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.page.html',
  styleUrls: ['./menu.page.scss'],
})
export class MenuPage implements OnInit {

  public appPages = [
    {
      title: 'Home',
      url: '/menu/home',
      icon: 'home'
    },
    {
      title: 'Add user',
      url: '/menu/adduser',
      icon: 'add'
    },
    
  ];
  selectedPath='';
  constructor(private router:Router) {
     this.router.events.subscribe((event: RouterEvent)=>{
       if(event&& event.url){
         this.selectedPath= event.url;

       }
     });
   }

  ngOnInit() {
  }

}
