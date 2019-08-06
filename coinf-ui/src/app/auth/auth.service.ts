import { Injectable } from '@angular/core';
import { OAuthService, OAuthEvent } from 'angular-oauth2-oidc';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { authConfig } from './auth.config';
import { Region } from '../shared/models/region.interface';
import { Store } from '@ngrx/store';
import * as fromRoot from '../core/store/app.reducer';
import * as authActions from './store/auth.actions';
import { Game } from '../shared/models/game.interface';

import * as jwt_decode from 'jwt-decode';
import { Authentication } from '../shared/models/authentication.interface';

export const GAME_URL = 'http://localhost:8082/game/';
export const FOO_URL = 'http://localhost:8082/foos/';
export const REGION_URL = 'http://localhost:8082/regions/';

@Injectable()
export class AuthService {

  constructor(
    private router: Router,
    private _http: HttpClient,
    private oauthService: OAuthService,
    private store: Store<fromRoot.State>
  ) {
    this.oauthService.events.subscribe((event: OAuthEvent) => {
      console.log(event);
      if (event.type === 'logout') {
        this.store.dispatch(new authActions.SetUnauthenticated());
      }
    });
    this.oauthService.configure(authConfig);
    this.oauthService.setStorage(sessionStorage);
    this.oauthService.tryLogin()
      .then(() => {
        if (this.oauthService.hasValidAccessToken()) {
          console.log('User has valid access token.');
          let authentication: Authentication =
            <Authentication> jwt_decode(this.oauthService.getAccessToken());

          this.store.dispatch(new authActions.SetAuthenticated(authentication));
        } else {
          console.log('User is not logged in.');
        }
      })
      .catch(err => {
        console.error(err);
      });
  }

  obtainAccessToken(): void {
    console.log('Obtaining access token from authorization server.');
    this.oauthService.initImplicitFlow();
  }


  logout() {
    console.log('Discarding token to logout.');
    this.oauthService.logOut();
    this.router.navigate(['/']);
  }

  getGame(): Observable<Game> {
    return this._http
      .get<Game>(`${GAME_URL}`)
      .pipe(catchError((error: any) => throwError(error)));
  }

  createGame(): Observable<any> {
    return this._http
      .post<any>(`${GAME_URL}`, null)
      .pipe(catchError((error: any) => throwError(error)));
  }

  getRegions(): Observable<Region[]> {
    return this._http
      .get<Region[]>(REGION_URL)
      .pipe(catchError((error: any) => throwError(error)));
  }

  isLoggedIn(): boolean {
    if (this.oauthService.getAccessToken() === null) {
      return false;
    }
    return true;
  }

}
