import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../core/services/user.service';
import * as fromRoot from '../../../core/store/app.reducer';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { UserMessage } from 'src/app/shared/models/user-message.interface';
import { WebSocketService } from 'src/app/core/services/websocket.service';

@Component({
  selector: 'app-lobby',
  templateUrl: './lobby.component.html',
  styleUrls: ['./lobby.component.css']
})
export class LobbyComponent implements OnInit {

  messages$: Observable<UserMessage[]>;

  constructor(
    private webSocketService: WebSocketService,
    private store: Store<fromRoot.State>
  ) { }

  ngOnInit() {
    this.messages$ = this.store.select(fromRoot.getUserMessages);
  }

  sendInvite() {
    this.webSocketService.sendMessageToSelf();
  }

}
