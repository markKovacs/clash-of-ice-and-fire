package com.coinf.controller;

import com.coinf.dto.GameDto;
import com.coinf.entity.instance.Account;
import com.coinf.entity.instance.AccountStatistics;
import com.coinf.service.GameService;
import com.coinf.util.AuthExtractionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class GameController {

    @Autowired
    private AuthExtractionUtil authExtractUtil;

    @Autowired
    private GameService gameService;

    @PostMapping(value = "/game")
    public void createGame(Authentication auth) {
        // TODO: accounts should come from post body and auth maybe not needed

        List<Account> mockAccounts = new ArrayList<>();
        mockAccounts.add(new Account("tom@gmail.com", "tom", new AccountStatistics()));
        mockAccounts.add(new Account("mark@gmail.com", "mark", new AccountStatistics()));
        mockAccounts.add(new Account("deb@gmail.com", "deb", new AccountStatistics()));

        gameService.createGame(mockAccounts);
    }

    @GetMapping(value = "/game")
    public GameDto getGame(Authentication auth) {
        String authName = authExtractUtil.getAuthName(auth);
        return gameService.getGame(authName);
    }

    @PreAuthorize("#oauth2.hasScope('foo') and #oauth2.hasScope('read')")
    @GetMapping(value = "/check")
    public String check(Authentication auth) {
        return "Your auth name: " + authExtractUtil.getAuthName(auth) + ". Randomized UUID: " + UUID.randomUUID();
    }

}
