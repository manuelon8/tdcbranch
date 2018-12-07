package com.accenture.cardsproject.exceptions;

public class InvalidParameterException extends ValidationException{

    private static final long serialVersionUID = 1L;

    public InvalidParameterException(String parameter) {
        super(String.format("El parámetro ingresado (%s) es inválido.", parameter));
    }
}
