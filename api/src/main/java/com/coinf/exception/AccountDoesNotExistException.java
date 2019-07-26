package com.coinf.exception;

public class AccountDoesNotExistException extends RuntimeException {

    public AccountDoesNotExistException(String userName) {
        super("Account not found with user name: " + userName);
    }

}
