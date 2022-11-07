package com.ufrn.exception;

public class UserNotExistException extends RuntimeException{

    public UserNotExistException() {
        super("Usuario nao existe");
    }
}
