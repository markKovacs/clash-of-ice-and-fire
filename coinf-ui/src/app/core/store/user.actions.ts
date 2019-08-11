import { Action } from '@ngrx/store';
import { UserMessage } from 'src/app/shared/models/messages/user-message.interface';

export const ADD_USER_MESSAGE = '[Core] Add User Message';

export class AddUserMessage implements Action {
  readonly type = ADD_USER_MESSAGE;
  constructor(public payload: UserMessage) {}
}

export type UserActions = AddUserMessage;
