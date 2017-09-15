import { Injectable } from '@angular/core';
import {Http , Headers} from '@angular/http';
import 'rxjs/add/observable/throw';

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
}
