import { Injectable } from '@angular/core';
import * as fromRoot from '../store/app.reducer';
import { Store } from '@ngrx/store';

@Injectable()
export class UserService {

  constructor(
    private store: Store<fromRoot.State>
  ) {}

}
