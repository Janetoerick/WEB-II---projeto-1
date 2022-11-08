package com.ufrn.exception;

public class EquipamentoNotExistException extends RuntimeException{

    public EquipamentoNotExistException() {
        super("Equipamento nao existe");
    }
}
