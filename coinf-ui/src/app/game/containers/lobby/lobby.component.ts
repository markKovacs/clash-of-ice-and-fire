import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../core/services/user.service';
import * as fromRoot from '../../../core/store/app.reducer';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { WhisperMessage } from 'src/app/shared/models/messages/whisper-message.interface';
import { InviteMessage } from 'src/app/shared/models/messages/invite-message.interface';
import { SystemMessage } from 'src/app/shared/models/messages/system-message.interface';
import { ChatMessage } from 'src/app/shared/models/messages/chat-message.interface';

@Component({
  selector: 'app-lobby',
  templateUrl: './lobby.component.html',
  styleUrls: ['./lobby.component.css']
})
export class LobbyComponent implements OnInit {

  whispers$: Observable<WhisperMessage[]>;
  invites$: Observable<InviteMessage[]>;
  systemMessages$: Observable<SystemMessage[]>;
  chatMessages$: Observable<ChatMessage[]>;

  constructor(
    private userService: UserService,
    private store: Store<fromRoot.State>
  ) { }

  ngOnInit() {
    this.whispers$ = this.store.select(fromRoot.getWhispers);
    this.invites$ = this.store.select(fromRoot.getInvites);
    this.systemMessages$ = this.store.select(fromRoot.getSystemMessages);
    this.chatMessages$ = this.store.select(fromRoot.getChatMessages);
  }

  sendMessage() {
    this.userService.sendWhisper('mark@mark.com', 'Hello Mark!');
  }

  sendInvite() {
    this.userService.sendInvite('mark@mark.com');
  }

}
