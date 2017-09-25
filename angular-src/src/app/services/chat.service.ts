import { Injectable , OnInit } from '@angular/core';
import {Http , Headers , Response} from '@angular/http';
import 'rxjs/add/observable/throw';
import {Observable} from 'rxjs/Rx';
import {tokenNotExpired} from 'angular2-jwt';
import * as io from 'socket.io-client';
// Operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class ChatService {
  //private url = 'http://localhost:4000'; 
  private socket;
  messages = [];
  constructor() {
    //this.socket = io.connect('http://127.0.0.1:4000');
   }
  
  //  ngOnInit() {
  //    this.socket = io.connect('http://127.0.0.1:4000');
  //  }

  getMessages() {
    let observable = new Observable(observer => {
     // console.log("hello");
      this.socket = io.connect('http://127.0.0.1:4000');
      //console.log(this.socket);
      this.socket.on('updatedMessages', (data) => {
       console.log(data);
             if(data.length){
                for(let x = 0;x < data.length;x++){
                    // Build out message div
                    //if(x==(data.length-1)){
                      //console.log(data[x].username+data[x].message);
                    let message  = data[x].username+": "+data[x].message;
                    this.messages.splice(0,data.length);
                    this.messages.push(message);
                    //}
                }
            }
            //console.log(this.messages);
           observer.next(this.messages);     
      });
      return () => {
        this.socket.disconnect();
      };  
    })     
    return observable;
  }   

  sendMessage(message,username){
    //this.getMessages();
    // Make sure the "add-message" is written here because this is referenced in on() in our server
    //this.socket = io.connect('http://127.0.0.1:4000');
    //console.log(message+username);
  // console.log(this.socket);
  let data={
    "message":message,
    "username":username
  };
  //console.log(data);
  //console.log(this.socket);
    this.socket.emit('input',data);   
  }



  // getMessages() {
  //    this.socket = io.connect('http://127.0.0.1:4000');
  //    console.log("getting messages......");
  // // let observable = new Observable(observer => {
  // this.socket.on('updatedMessages', (data) => {
  //     console.log(data);
  //   //     if(data.length){
  //   //       for(let x = 0;x < data.length;x++){
  //   //           // Build out message div
  //   //           let message = document.createElement('div');
  //   //           message.setAttribute('class', 'chat-message');
  //   //           message.textContent = data[x].name+": "+data[x].message;
  //   //           this.messages.push(message);
  //   //           this.messages.insertBefore(message, this.messages.firstChild);
  //   //       }
  //   //   }
  //   //  observer.next(this.messages);   
  // });
  // return () => {
  // this.socket.disconnect();
  // }; 
  // })    
  // return observable;
   //} 
}
