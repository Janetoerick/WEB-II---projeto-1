package com.ufrn.exception;

public class UserNameNotFoundException extends RuntimeException{

    public UserNameNotFoundException() {
        super("Nome de usuario nao encontrado");
    }
}
