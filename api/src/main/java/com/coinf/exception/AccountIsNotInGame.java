package com.coinf.exception;

public class AccountIsNotInGame extends RuntimeException {

    public AccountIsNotInGame(String userName) {
        super("Account " + userName + " is currently not in game.");
    }

}
