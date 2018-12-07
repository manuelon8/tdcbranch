package com.accenture.cardsproject.exceptions;

public class InvalidDateException extends ValidationException{
    private static final long serialVersionUID = 1L;

    public InvalidDateException(String parameter) {
        super(String.format("La tarjeta es inválida, la fecha de vencimiento es = %s.", parameter));
    }
}
