package com.coinf.messages;

import com.coinf.entity.enums.UserMessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WhisperMessage extends UserMessage {

    public WhisperMessage() {
        super(UserMessageType.WHISPER);
    }

    private String from;
    private String to;
    private String message;

}
