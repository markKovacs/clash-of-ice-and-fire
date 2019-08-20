import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Subject } from 'rxjs';
import * as fromStore from '../store/app.reducer';
import { Store } from '@ngrx/store';
import { Game } from 'src/app/shared/models/game.interface';
import { WhisperMessage } from 'src/app/shared/models/messages/whisper-message.interface';
import { InviteMessage } from 'src/app/shared/models/messages/invite-message.interface';
import * as userActions from '../store/user.actions';

@Injectable()
export class WebSocketService {

  // Maybe store websocket subscriptions internally in a map and lookup by id to unsubscribe if needed

  private serverUrl = 'http://localhost:8082/socket';
  private stompClient: any;
  // TODO: delete these below if not needed
  private gameSubscription: any;
  private gameMessages$: Subject<Game>;

  constructor(private store: Store<fromStore.State>) {}

  initializeWebSocketConnection() {
    console.log('Initializing websocket connection.');
    const ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    this.stompClient.connect(
      {},
      (frame) => {
        console.log('Websocket connection established:', frame);
        this.store.select(fromStore.getUserName).subscribe((userName) => {

          const USER_TOPIC = `/topic/users/${userName}`;
          console.log('Subscribing to ' + USER_TOPIC);
          that.stompClient.subscribe(USER_TOPIC, (message) => {
            if (message.body) {
              const msg = JSON.parse(message.body);
              console.log('Message received to user subscription: ', msg);
              switch (msg.type) {
                case 'WHISPER':
                  this.store.dispatch(new userActions.AddWhisperMessage(msg));
                  break;
                case 'INVITE':
                  this.store.dispatch(new userActions.AddInviteMessage(msg));
                  break;
                case 'WELCOME':
                  this.store.dispatch(new userActions.AddSystemMessage(msg));
                  break;
              }
            }
          });
          const GENERAL_TOPIC = `/topic/general`;
          console.log('Subscribing to ' + GENERAL_TOPIC);
          that.stompClient.subscribe(GENERAL_TOPIC, (message) => {
            if (message.body) {
              const msg = JSON.parse(message.body);
              console.log('Message received to general subscription: ', msg);
              switch (msg.type) {
                case 'SYSTEM':
                  this.store.dispatch(new userActions.AddSystemMessage(msg));
                  break;
                case 'CHAT':
                  this.store.dispatch(new userActions.AddChatMessage(msg));
                  break;
              }
            }
          });
        });
      },
      (err) => console.log('Websocket connection failed:', err)
    );
  }

  sendWhisper(whisper: WhisperMessage) {
    if (this.stompClient.connected) {
      const destination = '/ws/whisper';
      console.log('Sending whisper', whisper, 'to destination', destination);
      this.stompClient.send(destination, {}, JSON.stringify(whisper));
    } else {
      alert('Connection broken, cannot send message. Please try to reconnect.');
    }
  }

  sendInvite(invite: InviteMessage) {
    if (this.stompClient.connected) {
      const destination = '/ws/invite';
      console.log('Sending invite', invite, 'to destination', destination);
      this.stompClient.send(destination, {}, JSON.stringify(invite));
    } else {
      alert('Connection broken, cannot send message. Please try to reconnect.');
    }
  }

  subscribeToGameUpdates(gameId: string): any {
    // TODO: still need to test it
    if (this.stompClient.connected) {
      const topic = `/topic/games/${gameId}`;
      console.log('Subscribing to topic', topic);
      return this.stompClient.subscribe(topic, (message) => {
        // TODO: add handler for gameUpdate message
      });
    } else {
      alert('Connection broken, cannot subscribe to game. Please try to reconnect.');
    }
  }

}
