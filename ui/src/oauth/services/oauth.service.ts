import { Injectable } from '@angular/core';
import { OAuthService as OAuth2Service } from 'angular-oauth2-oidc';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { authConfig } from '../config/oauth.config';


export interface Foo {
  id: number,
  name: string
}

@Injectable()
export class OAuthService {

  constructor(
    private _router: Router,
    private _http: HttpClient,
    private oauthService: OAuth2Service
  ) {
    this.oauthService.configure(authConfig);
    this.oauthService.setStorage(sessionStorage);
    this.oauthService.tryLogin()
      .then(() => {
        if (!this.oauthService.hasValidAccessToken()) {
          console.log('User is not logged in.');
        } else {
          console.log('User has previously logged in.');
        }
      })
      .catch(err => {
        console.error(err);
      });
  }

  obtainAccessToken(): void {
    this.oauthService.initImplicitFlow();
  }

  getResource(resourceUrl: any): Observable<any> {
    var headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + this.oauthService.getAccessToken()
    });
    return this._http
      .get(resourceUrl, { headers })
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  isLoggedIn(): boolean {
    if (this.oauthService.getAccessToken() === null) {
      return false;
    }
    return true;
  }

  logout(): void {
    this.oauthService.logOut();
    this._router.navigate(['/']);
  }

}
