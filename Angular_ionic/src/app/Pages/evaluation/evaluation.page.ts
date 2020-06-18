import { Component, OnInit } from '@angular/core';
import { gitModel } from 'src/app/model/Git';
import { hackModel } from 'src/app/model/Hack';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Name } from 'src/app/model/Name';

@Component({
  selector: 'app-evaluation',
  templateUrl: './evaluation.page.html',
  styleUrls: ['./evaluation.page.scss'],
})
export class EvaluationPage implements OnInit {
  isValidFormSubmitted = false;
  name: Name[]=[
    { name:'sooraj'},
    { name:'meharu'},
    { name:'ajith'},
    { name:'abhi'},
    { name:'sanu'},
    { name:'jose'},
    { name:'mani'},
    { name:'anju'},
    { name:'gokhul'},
    
  ];
  modelGit : gitModel={
    name:"",
    mark:""
  }
  modelHack : hackModel={
    name:"",
    mark:""
  }


  user: object;
  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }
  sendFeedback():void{

    console.log("COnsole test for GIT Model"+this.modelGit.name+"GIT MARK = "+this.modelGit.mark);

    let Url:string = "http://localhost:8080/api/gits";
   
    this.http.post(Url,this.modelGit).subscribe(data => {
      alert("GitMark Updated successfully");
   
    },
    err => {
      alert('GitMark Updation failed' + console.error() );
    });
    
    
    console.log("COnsole test for Hack Model"+this.modelHack.name+"HACKATHON MARK = "+this.modelHack.mark);

    let Url1:string = "http://localhost:8080/api/hackathons";
   
    this.http.post(Url1,this.modelHack).subscribe(data => {
      alert("HackaThon Mark Updated successfully");
   
    },
    err => {
      alert('HackaThon Mark Updation failed' + console.error() );
    });
    this.router.navigateByUrl('/menu/home');


  }   



  sendFeedbackk(): void{

    console.log("COnsole test for Hack Model"+this.modelHack.name+"HACKATHON MARK = "+this.modelHack.mark);

    let Url:string = "http://localhost:8080/api/hackathons";
   
    this.http.post(Url,this.modelGit).subscribe(data => {
      alert("HackaThon Mark Updated successfully");
   
    },
    err => {
      alert('HackaThon Mark Updation failed' + console.error() );
    });
    this.router.navigateByUrl('/menu/home');
  }   
  
}
