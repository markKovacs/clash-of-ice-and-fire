import { Injectable } from '@angular/core';
import { OAuthService as OAuth2Service } from 'angular-oauth2-oidc';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { authConfig } from '../oauth.config';

export const FOO_URL: string = 'http://localhost:8082/foos/';

export const REGION_URL: string = 'http://localhost:8082/regions/';
export interface Foo {
  id: number,
  name: string
}

export interface Region {
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

  getFoo(id: number): Observable<Foo> {
    return this._http
      .get<Foo>(`${FOO_URL}${id}`)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  getRegions(): Observable<Region[]> {
    return this._http
      .get<Region[]>(REGION_URL)
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