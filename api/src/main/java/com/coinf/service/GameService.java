package com.coinf.service;

import com.coinf.dto.GameDto;
import com.coinf.entity.blueprint.GameEvent;
import com.coinf.entity.enums.GameEventType;
import com.coinf.entity.instance.Account;
import com.coinf.entity.instance.Game;
import com.coinf.exception.AccountDoesNotExistException;
import com.coinf.exception.AccountIsNotInGame;
import com.coinf.repository.AccountRepository;
import com.coinf.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameInitializer gameInitializer;
    @Autowired
    private GameMapper gameMapper;

    // TODO: Use annotations at service or controller layer to limit boiler plate code, permissions?
    // TODO: Use transactions if needed

    public void createGame(List<Account> accounts) {

        // TODO: only for testing until registration is not live
        accountRepository.saveAll(accounts);

        Game game = gameInitializer.init(accounts);
        gameRepository.save(game);
    }

    public GameDto getGame(String userName) {
        // TODO: Simple implementation for testing. Needs work.
        Account account = accountRepository.getByUserName(userName);
        if (account == null) {
            throw new AccountDoesNotExistException(userName);
        }
        if (account.getPlayer() == null) {
            throw new AccountIsNotInGame(userName);
        }
        Game game = account.getPlayer().getGame();
        return gameMapper.transform(game, userName);
    }

    public boolean eventHappened(Game game, GameEventType type) {
        return game.getEventsThisTurn()
                .stream()
                .map(GameEvent::getType)
                .anyMatch(t -> t.equals(type));
    }

}
