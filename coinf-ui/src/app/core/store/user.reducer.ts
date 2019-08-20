import { WhisperMessage } from 'src/app/shared/models/messages/whisper-message.interface';
import { InviteMessage } from 'src/app/shared/models/messages/invite-message.interface';
import { UserActions, ADD_WHISPER_MESSAGE, ADD_INVITE_MESSAGE, ADD_SYSTEM_MESSAGE, ADD_CHAT_MESSAGE } from './user.actions';
import { SystemMessage } from 'src/app/shared/models/messages/system-message.interface';
import { ChatMessage } from 'src/app/shared/models/messages/chat-message.interface';

export interface State {
  whispers: WhisperMessage[];
  invites: InviteMessage[];
  systemMessages: SystemMessage[];
  chatMessages: ChatMessage[];
}

const initialState: State = {
  whispers: [],
  invites: [],
  systemMessages: [],
  chatMessages: []
};

export function userReducer(state = initialState, action: UserActions) {
  switch (action.type) {
    case ADD_WHISPER_MESSAGE:
      console.log('Storing whisper message:', action.payload);
      const whispers = [...state.whispers, action.payload];
      return {
        ...state,
        whispers
      };

    case ADD_INVITE_MESSAGE:
      console.log('Storing invite message:', action.payload);
      const invites = [...state.invites, action.payload];
      return {
        ...state,
        invites
      };

    case ADD_SYSTEM_MESSAGE:
      console.log('Storing system message:', action.payload);
      const systemMessages = [...state.systemMessages, action.payload];
      return {
        ...state,
        systemMessages
      };

    case ADD_CHAT_MESSAGE:
      console.log('Storing chat message:', action.payload);
      const chatMessages = [...state.chatMessages, action.payload];
      return {
        ...state,
        chatMessages
      };

    default: {
      return state;
    }
  }
}

export const getWhispers = (state: State) => state.whispers;
export const getInvites = (state: State) => state.invites;
export const getSystemMessages = (state: State) => state.systemMessages;
export const getChatMessages = (state: State) => state.chatMessages;
