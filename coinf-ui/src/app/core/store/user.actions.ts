import { Action } from '@ngrx/store';
import { InviteMessage } from 'src/app/shared/models/messages/invite-message.interface';
import { WhisperMessage } from 'src/app/shared/models/messages/whisper-message.interface';
import { SystemMessage } from 'src/app/shared/models/messages/system-message.interface';
import { ChatMessage } from 'src/app/shared/models/messages/chat-message.interface';

export const ADD_WHISPER_MESSAGE = '[Core] Add Whisper Message';
export const ADD_INVITE_MESSAGE = '[Core] Add Invite Message';
export const ADD_SYSTEM_MESSAGE = '[Core] Add System Message';
export const ADD_CHAT_MESSAGE = '[Core] Add Chat Message';

export class AddWhisperMessage implements Action {
  readonly type = ADD_WHISPER_MESSAGE;
  constructor(public payload: WhisperMessage) {}
}

export class AddInviteMessage implements Action {
  readonly type = ADD_INVITE_MESSAGE;
  constructor(public payload: InviteMessage) {}
}

export class AddSystemMessage implements Action {
  readonly type = ADD_SYSTEM_MESSAGE;
  constructor(public payload: SystemMessage) {}
}

export class AddChatMessage implements Action {
  readonly type = ADD_CHAT_MESSAGE;
  constructor(public payload: ChatMessage) {}
}

export type UserActions = AddWhisperMessage | AddInviteMessage | AddSystemMessage | AddChatMessage;
