import { Action } from '@ngrx/store';
import { Authentication } from 'src/app/shared/models/authentication.interface';

export const SET_AUTHENTICATED = '[Auth] Set Authenticated';
export const SET_UNAUTHENTICATED = '[Auth] Set Unauthenticated';
export const LOGIN = '[Auth] Login';
export const LOGOUT = '[Auth] Logout';

export class SetAuthenticated implements Action {
  readonly type = SET_AUTHENTICATED;
  constructor(public payload: Authentication) {}
}

export class SetUnauthenticated implements Action {
  readonly type = SET_UNAUTHENTICATED;
}

export class Login implements Action {
  readonly type = LOGIN;
}

export class Logout implements Action {
  readonly type = LOGOUT;
}

export type AuthActions = SetAuthenticated | SetUnauthenticated | Login | Logout;
