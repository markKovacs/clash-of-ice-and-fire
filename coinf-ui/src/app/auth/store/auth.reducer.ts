import { AuthActions, SET_AUTHENTICATED, SET_UNAUTHENTICATED } from './auth.actions';
import { Authentication } from 'src/app/shared/models/authentication.interface';

export interface State {
  isAuthenticated: boolean;
  authentication: Authentication;
}

const initialState: State = {
  isAuthenticated: false,
  authentication: null
};

export function authReducer(state = initialState, action: AuthActions) {
  switch (action.type) {
    case SET_AUTHENTICATED:
      console.log('Authenticated successfully, storing token in session storage.');
      return {
        isAuthenticated: true,
        authentication: action.payload
      };
    case SET_UNAUTHENTICATED:
      console.log('Logged out, token discarded.');
      return {
        isAuthenticated: false,
        authentication: null
      };
    default: {
      return state;
    }
  }
}

export const getIsAuth = (state: State) => state.isAuthenticated;
export const getAuth = (state: State) => state.authentication;
