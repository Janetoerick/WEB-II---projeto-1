package com.ufrn.exception;

public class ReservaNotExistException extends RuntimeException{

    public ReservaNotExistException() {
        super("Reserva nao existe");
    }
}
