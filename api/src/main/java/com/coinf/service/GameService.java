package com.coinf.service;

import com.coinf.dto.GameDto;
import com.coinf.entity.instance.Account;
import com.coinf.entity.instance.Game;
import com.coinf.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private AccountRepository accountRepository;

    public GameDto getGame(String userName) {
        Account account = accountRepository.getByUserName(userName);
        Game game = account.getPlayer().getGame();
        // TODO: create game dto from game, adding all stuff that should be in dto
        return null;
    }
}
