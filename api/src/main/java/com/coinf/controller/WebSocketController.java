package com.coinf.controller;

import com.coinf.messages.UserMessage;
import com.coinf.entity.enums.UserMessageType;
import com.coinf.messages.WhisperMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
@Slf4j
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate msgSender;

    @MessageMapping("/whisper")
    public void userMessage(WhisperMessage message) {

        log.info("Whisper message received: " + message);
        message.setTime(new Date());

        msgSender.convertAndSend("/topic/users/" + message.getTo(), message);
    }

    @SubscribeMapping("/users/{userName}")
    public UserMessage userJoined(@DestinationVariable String userName) {
        System.out.println("JOINED");
        return UserMessage.builder()
                .type(UserMessageType.LOGGED_IN)
                .message("User logged in: " + userName)
                .sentBy(userName)
                .time(new Date())
                .build();
    }

}
