package com.coinf.messages;

import com.coinf.entity.enums.UserMessageType;
import lombok.Data;

import java.util.Date;

@Data
public abstract class UserMessage {

    UserMessage(UserMessageType type) {
        this.time = new Date();
        this.type = type;
    }

    private UserMessageType type;
    private Date time;

}
