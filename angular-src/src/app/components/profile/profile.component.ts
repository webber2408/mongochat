import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user = Object;
  constructor(private authService:AuthService,
              private router:Router) { }

  ngOnInit() {
    console.log("Hello");
    this.authService.getProfile().subscribe(profile => {
       console.log(profile);
       
       this.user = profile.user;
    },
  err =>  {
      console.log(err);
      return false;
  });
  }

}
