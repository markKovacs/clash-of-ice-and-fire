package com.coinf.messages;


import com.coinf.entity.enums.UserMessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SystemMessage extends UserMessage {

    private static final String WELCOME_MSG = "Welcome %s!";
    private static final String USER_LOGIN_MSG = "User %s logged in.";

    private String message;

    private SystemMessage(String message, UserMessageType type) {
        super(type);
        this.message = message;
    }

    public static SystemMessage createWelcomeMessage(String userName) {
        return new SystemMessage(String.format(WELCOME_MSG, userName), UserMessageType.WELCOME);
    }

    public static SystemMessage createUserLoginMessage(String userName) {
        return new SystemMessage(String.format(USER_LOGIN_MSG, userName), UserMessageType.SYSTEM);
    }

}
