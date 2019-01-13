import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Store } from 'store';
import 'rxjs/add/operator/do';
import { BACKEND_API } from '../../../../constants';
import { User } from '../../../../shared/model/user.interface';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class AuthService {

  constructor(
    private store: Store,
    private http: HttpClient,
    private router: Router
  ) { }

  login(user: User) {
    let url = BACKEND_API + '/login';
    this.http.post<Observable<boolean>>(url, {
      userName: user.userName,
      password: user.password
    }).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem('token', btoa(user.userName + ':' + user.password));
        this.router.navigate(['']);
      } else {
        alert("Authentication failed.")
      }
    });
  }
  loginUser(email: any, password: any) { }

}