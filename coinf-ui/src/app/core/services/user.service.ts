import { Injectable } from '@angular/core';
import * as fromRoot from '../store/app.reducer';
import { Store } from '@ngrx/store';
import { WhisperMessage } from 'src/app/shared/models/messages/whisper-message.interface';
import * as fromStore from '../store/app.reducer';
import { WebSocketService } from './websocket.service';
import { InviteMessage } from 'src/app/shared/models/messages/invite-message.interface';

@Injectable()
export class UserService {

  userName: string = null;

  constructor(
    private store: Store<fromRoot.State>,
    private webSocketService: WebSocketService
  ) {
    this.store.select(fromStore.getUserName).subscribe((userName) => {
      this.userName = userName;
    });
  }

  sendWhisper(to: string, message: string) {
    if (this.userName) {
      const whisper: WhisperMessage = {to, from: this.userName, message};
      // should I receive something from server to know if ws whisper was sent successfully?
      this.webSocketService.sendWhisper(whisper);
    } else {
      console.log('Could not send whisper, username not stored successfully after authentication.');
    }
  }

  sendInvite(to: string) {
    if (this.userName) {
      const invite: InviteMessage = {to, from: this.userName};
      // should I receive something from server to know if ws invite was sent successfully?
      this.webSocketService.sendInvite(invite);
    } else {
      console.log('Could not send invite, username not stored successfully after authentication.');
    }
  }

}
