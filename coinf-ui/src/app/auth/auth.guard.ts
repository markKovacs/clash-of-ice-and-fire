import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  CanLoad,
  Route,
  Router
} from '@angular/router';
import { Store } from '@ngrx/store';
import { take, tap } from 'rxjs/operators';

import * as fromRoot from '../core/store/app.reducer';

@Injectable()
export class AuthGuard implements CanActivate, CanLoad {
  constructor(
    private store: Store<fromRoot.State>,
    private router: Router
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.store.select(fromRoot.getIsAuth).pipe(take(1));
  }

  canLoad(route: Route) {
    return this.store.select(fromRoot.getIsAuth).pipe(
      take(1),
      tap(isAuth => {
        if (!isAuth) {
          this.router.navigate(['']);
        }
      })
    );
  }
}