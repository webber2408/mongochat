import { Component, OnInit } from '@angular/core';
import {FlashMessagesService} from 'angular2-flash-messages';
import {ValidateService} from '../../services/validate.service';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-jobex',
  templateUrl: './jobex.component.html',
  styleUrls: ['./jobex.component.css']
})
export class JobexComponent implements OnInit {

  companyName:String;
  experience:String;
  exp = [];

  constructor(private validateService: ValidateService ,
    private flashMessage:FlashMessagesService,
    private authService: AuthService,
    private router:Router) { }

  ngOnInit() {
    this.authService.getExperience().subscribe(profile => {
      //console.log(profile);
      console.log(profile.results);
      this.exp = profile.results;
      console.log(this.exp)
      //this.user = profile.user;
   },
 err =>  {
     console.log(err);
     return false;
 });
  }
 
  getExperience(){
    this.authService.getExperience().subscribe(profile => {
      //console.log(profile);
      console.log(profile.results);
      this.exp = profile.results;
      console.log(this.exp)
      //this.user = profile.user;
   },
 err =>  {
     console.log(err);
     return false;
 });
  }

  onJobexSubmit(){
    const exp = {
      companyName:this.companyName,
      experience:this.experience
    }

    //Required fields 
    if(!this.validateService.validateExperience(exp)){
      this.flashMessage.show("Please fill in all fields !",{
        cssClass: 'alert-danger',
        timeout:3000
      });
      return false;
  }

    //Start authservice work
    this.authService.addExperience(exp).subscribe(data => {
      if(data.success){
       this.flashMessage.show("Your experience has been recorded successfully !",{
         cssClass: 'alert-success',
         timeout:3000
       });
      }
      else{
       this.flashMessage.show("Something went wrong , please try again !",{
         cssClass: 'alert-danger',
         timeout:3000
       });
       this.router.navigate(['/jobex']);
      }
    });
  }

}
