import { Injectable } from '@angular/core';
import {Http , Headers , Response} from '@angular/http';
import 'rxjs/add/observable/throw';
import {tokenNotExpired} from 'angular2-jwt';

// Operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class AuthService {
  authToken: any;
 user: any ;

  constructor(private http:Http) { }


  registerUser(user){
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    return this.http.post('http://localhost:3000/users/register',user , {headers: headers}).map(res => res.json());
  }

  authenticateUser(user){
    let headers = new Headers(); 
    headers.append('Content-Type','application/json');
    return this.http.post('http://localhost:3000/users/authenticate',user , {headers: headers}).map(res => res.json());
  }

  getProfile(){
    let headers = new Headers(); 
    this.loadToken();
    headers.append('Content-Type','application/json');
    headers.append('Authorization',this.authToken);
    console.log(this.authToken);
    return this.http.get('http://localhost:3000/users/profile', {headers: headers}).map( (res : Response )=> {
      console.log(res.json());
      res.json();
  });
  }

  storeUserData(token , user){
    localStorage.setItem('id_token'  , token);
    localStorage.setItem('user', JSON.stringify(user));
    this.authToken = token;
    this.user = user;
  }
  
  loadToken(){
    const token = localStorage.getItem('id_token');
    console.log(token);
    this.authToken = token;
  }

  loggedIn(){
    return tokenNotExpired('id_token');
  }

  logout(){
    this.authToken = null;
    this.user = null;
    localStorage.clear();
  }
}
