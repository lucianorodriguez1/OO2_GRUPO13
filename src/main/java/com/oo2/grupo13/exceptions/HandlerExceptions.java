package com.oo2.grupo13.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(EmailYaExisteException.class)
    public ModelAndView manejarEmailYaExiste(EmailYaExisteException ex) {
    	ModelAndView mAV = new ModelAndView("error/email-ya-existe");
    	mAV.addObject("mensajeError", ex.getMessage());
        return mAV;
    }

}