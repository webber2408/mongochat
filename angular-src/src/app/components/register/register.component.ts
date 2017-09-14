import { Component, OnInit } from '@angular/core';
import {FlashMessagesService} from 'angular2-flash-messages';
import {ValidateService} from '../../services/validate.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  name: String;
  username: String;
  email: String;
  password:String;
  contact:Number;

  constructor(private validateService: ValidateService , private flashMessage:FlashMessagesService) { }

  ngOnInit() {
  }

  onRegisterSubmit(){
    // console.log(this.name);

    const user = {
       name:this.name,
       username:this.username,
       email:this.email,
       password:this.password,
       contact:this.contact
    }
    
    //Required fields 
    if(!this.validateService.validateRegister(user)){
        this.flashMessage.show("Please fill in all fields !",{
          cssClass: 'alert-danger',
          timeout:3000
        });
        return false;
    }

    //Required fields 
    if(!this.validateService.validateEmail(user.email)){
      this.flashMessage.show("Please provide correct email !",{
        cssClass: 'alert-danger',
        timeout:3000
      });
      return false;
  }
  }

}
