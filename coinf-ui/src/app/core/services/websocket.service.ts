import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Subject } from 'rxjs';
import * as fromStore from '../store/app.reducer';
import { Store } from '@ngrx/store';
import * as userActions from '../store/user.actions';

@Injectable()
export class WebSocketService {

  // Maybe store websocket subscriptions internally in a map and lookup by id to unsubscribe if needed

  private serverUrl = 'http://localhost:8082/socket';
  private stompClient;

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
          that.stompClient.subscribe(`/ws/users/${userName}`, (message) => {
            console.log(1);
            if (message.body) {
              this.store.dispatch(new userActions.AddUserMessage(message.body));
            }
          });
          that.stompClient.subscribe(`/ws/chat`, (message) => {
            console.log(3);
            if (message.body) {
              this.store.dispatch(new userActions.AddUserMessage(message.body));
            }
          });
        });
      },
      (err) => console.log('Websocket connection failed:', err)
    );
  }

  subscribeToTopic(topic: string, subject: Subject<any>): any {
    if (this.stompClient.status === 'CONNECTED') {
      const fullTopic = `${topic}`;
      console.log('Subscribing to websocket topic', fullTopic);
      return this.stompClient.subscribe(fullTopic, (message) => subject.next(message));
    }
    console.log('Subscribing failed because stomp client is of status', this.stompClient.status);
  }

  sendMessage(destination: string, message: string, headers: Object = {}) {
    if (this.stompClient.status === 'CONNECTED') {
      if (!headers) {
        headers = {};
      }
      const fullDestination = `/ws${destination}`;
      console.log('Sending websocket message', message, 'to destination', fullDestination, 'with headers', headers);
      this.stompClient.send(fullDestination, headers, message);
    }
    console.log('Sending message failed because stomp client is of status', this.stompClient.status);
  }

  // FOR TESTING
  sendMessageToSelf() {
    const that = this;
    this.store.select(fromStore.getUserName).subscribe((userName) => {
      that.stompClient.send(`/ws/users/${userName}`, {}, 'Just invite to self...');
    });
  }

}
