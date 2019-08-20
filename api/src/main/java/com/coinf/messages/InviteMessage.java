package com.coinf.messages;

import com.coinf.entity.enums.UserMessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InviteMessage extends UserMessage {

    public InviteMessage() {
        super(UserMessageType.INVITE);
    }

    private String from;
    private String to;

}