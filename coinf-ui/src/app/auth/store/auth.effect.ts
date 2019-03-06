import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Action } from '@ngrx/store';
import * as fromAuth from './auth.actions';
import { tap } from 'rxjs/operators';
import { AuthService } from '../auth.service';
import { Effect, Actions, ofType } from '@ngrx/effects';

@Injectable()
export class AuthEffects {

  @Effect({ dispatch: false })
  logout$: Observable<Action> = this.actions$.pipe(
    ofType<fromAuth.Logout>(fromAuth.LOGOUT),
    tap(() => {
      console.log('Initiated logging out.');
      this.authService.logout();
    })
  );

  @Effect({ dispatch: false })
  login$: Observable<Action> = this.actions$.pipe(
    ofType<fromAuth.Login>(fromAuth.LOGIN),
    tap(() => {
      console.log('Trying to obtain access token.');
      this.authService.obtainAccessToken();
    })
  );

  constructor(
    private actions$: Actions,
    private authService: AuthService
  ) {}

}
