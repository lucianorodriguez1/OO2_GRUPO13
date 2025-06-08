package com.oo2.grupo13.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo13.helpers.ViewRouteHelper;

import org.springframework.ui.Model;


@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(EmailYaExisteException.class)
    public ModelAndView manejarEmailYaExiste(EmailYaExisteException ex) {
    	ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMAIL_EXISTE_ERROR);
    	mAV.addObject("mensajeError", ex.getMessage());
        return mAV;
    }
    
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
