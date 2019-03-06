import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { Store } from '@ngrx/store';

import * as fromRoot from '../../store/app.reducer';
import * as fromAuth from '../../../auth/store/auth.actions';

@Component({
  selector: 'app-sidenav-list',
  templateUrl: './sidenav-list.component.html',
  styleUrls: ['./sidenav-list.component.css']
})
export class SidenavListComponent implements OnInit {

  @Output() closeSidenav = new EventEmitter<void>();
  isAuth$: Observable<boolean>;

  constructor(
    private store: Store<fromRoot.State>
  ) {}

  ngOnInit() {
    this.isAuth$ = this.store.select(fromRoot.getIsAuth);
  }

  onLogin() {
    this.store.dispatch(new fromAuth.Login());
    this.onClose();
  }

  onClose() {
    this.closeSidenav.emit();
  }

  onLogout() {
    this.store.dispatch(new fromAuth.Logout());
    this.onClose();
  }

}
