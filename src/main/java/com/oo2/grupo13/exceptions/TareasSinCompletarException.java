package com.oo2.grupo13.exceptions;

public class TareasSinCompletarException extends RuntimeException{
    public TareasSinCompletarException(String mensaje) {
        super(mensaje);
    }
}
