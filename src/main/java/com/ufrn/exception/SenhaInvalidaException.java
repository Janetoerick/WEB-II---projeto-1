package com.ufrn.exception;

public class SenhaInvalidaException extends RuntimeException{

    public SenhaInvalidaException() {
        super("Senha inválida");
    }
}
