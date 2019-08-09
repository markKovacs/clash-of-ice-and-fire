import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../../services/websocket.service';
import * as fromRoot from '../../../core/store/app.reducer';
import { Store } from '@ngrx/store';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private wsConnectionInited = false;

  constructor(
    private websocketService: WebSocketService,
    private store: Store<fromRoot.State>
  ) {}

  ngOnInit() {
    this.store.select(fromRoot.getIsAuth).subscribe((isAuth) => {
      if (isAuth && !this.wsConnectionInited) {
        this.websocketService.initializeWebSocketConnection();
        this.wsConnectionInited = true;
      }
    });
  }

}
