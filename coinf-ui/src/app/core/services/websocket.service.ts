import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Subject } from 'rxjs';
import * as fromStore from '../store/app.reducer';
import { Store } from '@ngrx/store';
import * as userActions from '../store/user.actions';
import { Game } from 'src/app/shared/models/game.interface';
import { WhisperMessage } from 'src/app/shared/models/messages/whisper-message.interface';

@Injectable()
export class WebSocketService {

  // Maybe store websocket subscriptions internally in a map and lookup by id to unsubscribe if needed

  private serverUrl = 'http://localhost:8082/socket';
  private stompClient: any;
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
              this.store.dispatch(new userActions.AddUserMessage(message.body));
              // TODO: maybe replace this handling
              // e.g. UserService methods is called like handleMessage(message.body)
            }
          });
          const CHAT_TOPIC = `/topic/chat`;
          console.log('Subscribing to ' + CHAT_TOPIC);
          that.stompClient.subscribe(CHAT_TOPIC, (message) => {
            if (message.body) {
              // this.store.dispatch(new userActions.AddUserMessage(message.body));
              // TODO: other type of handling needed
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
