package com.ufrn.exception;

public class ReservaInvalidaException extends RuntimeException{

    public ReservaInvalidaException() {
        super("Horario da reserva indisponivel!");
    }
}