package com.ufrn.exception;

public class ReservaInvalidaException extends RuntimeException{

    public ReservaInvalidaException() {
        super("Dados da reserva invalidos!");
    }
}