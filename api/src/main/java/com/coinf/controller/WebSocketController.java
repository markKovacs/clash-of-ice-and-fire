package com.coinf.controller;

import com.coinf.messages.InviteMessage;
import com.coinf.messages.SystemMessage;
import com.coinf.messages.WhisperMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate msgSender;

    @MessageMapping("/whisper")
    public void handleWhisper(WhisperMessage whisper) {
        log.info("Whisper message received: " + whisper);
        msgSender.convertAndSend("/topic/users/" + whisper.getTo(), whisper);
    }

    @MessageMapping("/invite")
    public void handleInvite(InviteMessage invite) {
        log.info("Invite message received: " + invite);
        msgSender.convertAndSend("/topic/users/" + invite.getTo(), invite);
    }

    @SubscribeMapping("/users/{userName}")
    public SystemMessage handleUserSubscription(@DestinationVariable String userName) {
        log.info("User subscribed to user topic: " + userName);
        msgSender.convertAndSend("/topic/general", SystemMessage.createUserLoginMessage(userName));
        return SystemMessage.createWelcomeMessage(userName);
    }

}
