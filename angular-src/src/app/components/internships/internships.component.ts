import { Component, OnInit } from '@angular/core';
import {FlashMessagesService} from 'angular2-flash-messages';
import {ValidateService} from '../../services/validate.service';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-internships',
  templateUrl: './internships.component.html',
  styleUrls: ['./internships.component.css']
})
export class InternshipsComponent implements OnInit {

  internship:String;
  intern = [];

  constructor(private validateService: ValidateService ,
    private flashMessage:FlashMessagesService,
    private authService: AuthService,
    private router:Router) { }

  ngOnInit() {
    this.authService.getInternship().subscribe(profile => {
      console.log(profile);
      //console.log(profile.results);
      this.intern = profile.results;
      //console.log(this.exp)
      //this.user = profile.user;
   },
 err =>  {
     console.log(err);
     return false;
 });
  }
 
  getInternship(){
    this.authService.getInternship().subscribe(profile => {
      console.log(profile);
      //console.log(profile.results);
      this.intern = profile.results;
      //console.log(this.exp)
      //this.user = profile.user;
   },
 err =>  {
     console.log(err);
     return false;
 });
  }

  onInternshipSubmit(){
    const intern = {
      internship:this.internship
    }
   console.log("hello");
    //Required fields 
    if(!this.validateService.validateInternship(intern)){
      console.log("hello");
      this.flashMessage.show("Please fill in all fields !",{
        cssClass: 'alert-danger',
        timeout:3000
      });
      return false;
  }

    //Start authservice work
    this.authService.addInternship(intern).subscribe(data => {
      if(data.success){
       this.flashMessage.show("Internship opportunity has been recorded successfully !",{
         cssClass: 'alert-success',
         timeout:3000
       });
      }
      else{
       this.flashMessage.show("Something went wrong , please try again !",{
         cssClass: 'alert-danger',
         timeout:3000
       });
       this.router.navigate(['/internships']);
      }
    });
  }

}




