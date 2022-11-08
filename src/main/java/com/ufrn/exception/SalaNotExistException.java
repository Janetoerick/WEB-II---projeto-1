package com.ufrn.exception;

public class SalaNotExistException extends RuntimeException{

    public SalaNotExistException() {
        super("Sala nao existe");
    }
}
