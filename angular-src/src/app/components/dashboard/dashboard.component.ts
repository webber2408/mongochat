import { Component, OnInit } from '@angular/core';
import {ChatService} from '../../services/chat.service';
import {AuthService} from '../../services/auth.service';
import * as io from 'socket.io-client';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  messages=[];
  connection;
  message;
  username;
  //status;
  //statusDefault:"";
  private socket;
   constructor(private chatService:ChatService,private authService:AuthService) {}
  
  //  setStatus(s){
  //     status = s;
  //     if(s !== this.statusDefault){
  //       var delay = setTimeout(function(){
  //           this.setStatus(this.statusDefault);
  //       }, 4000);
  //   }
  //  }

  sendMessage(){
    console.log(this.message+this.username);
    this.chatService.sendMessage(this.message,this.username);
  this.message = '';
  // this.connection = this.chatService.getMessages().subscribe(message => {
  //   this.messages=message;
  // })
  // console.log("dashboard.....");
  // this.chatService.getMessages();
  // console.log(this.messages);
  }
  onReloadChats(){
    this.chatService.getMessages().subscribe(message => {
      console.log(message);
       //this.messages=[];
       this.messages.push(message);
       //this.messages=[];
       //console.log(this.messages);
     })
  }
  ngOnInit() {
    //this.socket = io.connect('http://127.0.0.1:4000');
    // if(this.socket == undefined){
    //   console.log("cant connect to socket!");
    // }
    // else{
    //   console.log(" connect to socket!");
    // }
    // console.log(this.messages);
    if(this.messages != undefined){
      this.chatService.getMessages().subscribe(message => {
        console.log(message);
         //this.messages=[];
         this.messages.push(message);
         //this.messages=[];
         //console.log(this.messages);
       })
  }
  this.authService.getProfile().subscribe(profile => {
    //console.log(profile);
    
    this.username = profile.user.name;
 },
err =>  {
   console.log(err);
   return false;
});
    
  }

  // Let's unsubscribe our Observable
  // ngOnDestroy() {
  //   this.connection.unsubscribe();
  // }
  

}
