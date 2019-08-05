import { Injectable } from '@angular/core';
import {
  CanActivate,
  Router
} from '@angular/router';
import { Store } from '@ngrx/store';
import { take, tap, map } from 'rxjs/operators';

import * as fromRoot from '../app.reducer';

@Injectable()
export class WelcomeGuard implements CanActivate {
  constructor(
    private store: Store<fromRoot.State>,
    private router: Router
  ) {}

  canActivate() {
    return this.store.select(fromRoot.getIsAuth).pipe(
      take(1),
      tap(isAuth => {
        if (isAuth) {
          this.router.navigate(['/lobby']);
        }
      }),
      map(isAuth => !isAuth)
    );
  }

}
