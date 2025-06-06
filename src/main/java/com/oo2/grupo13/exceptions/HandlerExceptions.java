package com.oo2.grupo13.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(TareaNoEncontradaException.class)
    public String manejarTareaNoEncontrada(TareaNoEncontradaException ex, Model model) {
        model.addAttribute("mensaje", ex.getMessage());
        return "errorTarea";
    }

     @ExceptionHandler(ValoracionNoEncontradaException.class)
    public String manejarValoracionNoEncontrada(ValoracionNoEncontradaException ex, Model model) {
        model.addAttribute("mensaje", ex.getMessage());
        return "errorValoracion";
    }

    @ExceptionHandler(ValoracionInvalidaException.class)
    public String manejarValoracionInvalida(ValoracionInvalidaException ex, Model model) {
        model.addAttribute("mensaje", ex.getMessage());
        return "errorValoracion";
    }

    @ExceptionHandler(Exception.class)
    public String manejarErroresGenerales(Exception ex, Model model) {
        model.addAttribute("mensaje", "Ocurrió un error inesperado.");
        return "error";
    }
}
