package com.coinf.controller;

import com.coinf.dto.GameDto;
import com.coinf.service.GameService;
import com.coinf.util.AuthExtractionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    // TODO: This should return a GameDto, which is the current state of whole game,
    //  containing the whole board with unit and all info, and also other game info like player and faction mats.
    //  Could be separated into several services... use builder pattern for dto object constructions.

    @Autowired
    private AuthExtractionUtil authExtractUtil;

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/game")
    public GameDto getGame(Authentication auth) {
        String authName = authExtractUtil.getAuthName(auth);
        // TODO: Look up user based on authName, then you also get if it has a current active game or not,
        //  or even if he registered with an in-game username
        //  Important: we look up the Account and Game objects
        //  but we need to have a DTO generator on top of it to return a flattened object to frontend
        return gameService.getGame(authName);
    }

}
