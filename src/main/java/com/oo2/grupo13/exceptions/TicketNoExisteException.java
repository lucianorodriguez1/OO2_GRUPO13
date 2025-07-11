package com.oo2.grupo13.exceptions;

public class TicketNoExisteException extends RuntimeException {
    public TicketNoExisteException(Long id) {
        super("El ticket con ID " + id + " no existe.");
    }
}