package com.coinf.controller;

import com.coinf.dto.UserMessage;
import com.coinf.entity.enums.UserMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/users/{userName}")
    @SendTo("/ws/users/{userName}")
    public UserMessage userMessage(String message, @DestinationVariable String userName) throws Exception {
        System.out.println("RECEIVE MESSAGE FROM: " + userName);
        System.out.println("MESSAGE CONTENT: " + message);
        UserMessage msg = UserMessage.builder()
                .type(UserMessageType.INVITE)
                .message("You are invited to join a game by " + userName)
                .sentBy(userName)
                .time(new Date())
                .build();

        return msg;
    }

    @SubscribeMapping("/users/{userName}")
    public UserMessage userJoined(@DestinationVariable String userName) throws Exception {
        System.out.println("JOINED");
        return UserMessage.builder()
                .type(UserMessageType.LOGGED_IN)
                .message("User logged in: " + userName)
                .sentBy(userName)
                .time(new Date())
                .build();
    }

}
