package com.ufrn.exception;

public class UsuarioNotExistException extends RuntimeException{

    public UsuarioNotExistException() {
        super("Usuario nao existe");
    }
}