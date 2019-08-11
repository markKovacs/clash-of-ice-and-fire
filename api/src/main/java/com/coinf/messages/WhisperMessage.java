package com.coinf.messages;

import lombok.Data;

import java.util.Date;

@Data
public class WhisperMessage {

    private String from;
    private String to;
    private String message;
    private Date time;

}
