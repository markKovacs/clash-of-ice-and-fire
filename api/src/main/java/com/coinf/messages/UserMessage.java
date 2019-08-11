package com.coinf.messages;

import com.coinf.entity.enums.UserMessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UserMessage {

    private UserMessageType type;
    private String message;
    private String sentBy;
    private String sentTo;
    private Date time;

}
