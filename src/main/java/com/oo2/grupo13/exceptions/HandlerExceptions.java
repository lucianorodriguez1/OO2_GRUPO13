package com.oo2.grupo13.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo13.helpers.ViewRouterHelper;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(EmailYaExisteException.class)
    public ModelAndView manejarEmailYaExiste(EmailYaExisteException ex) {
    	ModelAndView mAV = new ModelAndView(ViewRouterHelper.EMAIL_EXISTE_ERROR);
    	mAV.addObject("mensajeError", ex.getMessage());
        return mAV;
    }

}