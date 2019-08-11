import { ADD_USER_MESSAGE, UserActions } from './user.actions';
import { UserMessage } from 'src/app/shared/models/messages/user-message.interface';

export interface State {
  messages: UserMessage[];
}

const initialState: State = {
  messages: []
};

export function userReducer(state = initialState, action: UserActions) {
  switch (action.type) {
    case ADD_USER_MESSAGE:
      console.log('Storing message for user. Message:', action.payload);
      const messages = [...state.messages, action.payload];
      return {
        ...state,
        messages
      };
    default: {
      return state;
    }
  }
}

export const getMessages = (state: State) => state.messages;
