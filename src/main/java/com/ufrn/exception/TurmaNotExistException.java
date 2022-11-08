package com.ufrn.exception;

public class TurmaNotExistException extends RuntimeException{

    public TurmaNotExistException() {
        super("Turma nao existe");
    }
}
