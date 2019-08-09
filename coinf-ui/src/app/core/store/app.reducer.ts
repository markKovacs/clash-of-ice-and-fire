import { ActionReducerMap, createFeatureSelector, createSelector } from '@ngrx/store';

import * as fromUi from '../../shared/store/ui.reducer';
import * as fromAuth from '../../auth/store/auth.reducer';
import * as fromUser from './user.reducer';

export interface State {
  ui: fromUi.State;
  auth: fromAuth.State;
  user: fromUser.State;
}

export const reducers: ActionReducerMap<State> = {
  ui: fromUi.uiReducer,
  auth: fromAuth.authReducer,
  user: fromUser.userReducer
};

export const getUiState = createFeatureSelector<fromUi.State>('ui');
export const getIsLoading = createSelector(getUiState, fromUi.getIsLoading);

export const getAuthState = createFeatureSelector<fromAuth.State>('auth');
export const getIsAuth = createSelector(getAuthState, fromAuth.getIsAuth);
export const getAuth = createSelector(getAuthState, fromAuth.getAuth);
export const getUserName = createSelector(getAuth, (auth) => auth.user_name);

export const getUserState = createFeatureSelector<fromUser.State>('user');
export const getUserMessages = createSelector(getUserState, fromUser.getMessages);
