package com.oo2.grupo13.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.oo2.grupo13.helpers.ViewRouteHelper;
import org.springframework.ui.Model;


@ControllerAdvice
public class HandlerExceptions {

//    @ExceptionHandler(EmailYaExisteException.class)
//    public ModelAndView manejarEmailYaExiste(EmailYaExisteException ex) {
//    	ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMAIL_EXISTE_ERROR);
//    	mAV.addObject("mensajeError", ex.getMessage());
//        return mAV;
//    }
	
	@ExceptionHandler(EmailYaExisteException.class)
	public ModelAndView manejarEmailYaExiste(EmailYaExisteException ex, HttpServletRequest request) {
	    ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMAIL_EXISTE_ERROR);
	    mAV.addObject("mensajeError", ex.getMessage());

	    // Determinar desde dónde vino la solicitud
	    String referer = request.getHeader("Referer");
	    
	    if (referer != null && referer.contains("/editar/")) {
	        mAV.addObject("volverUrl", referer); // volver al form de edición
	    } else {
	        mAV.addObject("volverUrl", "/cliente/nuevo"); // por defecto, volver al form de creación
	    }

	    return mAV;
	}

	
    @ExceptionHandler(TareaNoEncontradaException.class)
    public ModelAndView manejarTareaNoEncontrada(TareaNoEncontradaException ex) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.TAREA_NO_ENCONTRADA_ERROR);
    	mAV.addObject("mensaje", ex.getMessage());
        return mAV;
    }

    @ExceptionHandler(ValoracionInvalidaException.class)
    public ModelAndView manejarValoracionInvalida(ValoracionInvalidaException ex) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.VALORACION_INCORRECTA_ERROR);
    	mAV.addObject("mensaje", ex.getMessage());
        return mAV; 
    }

    @ExceptionHandler(Exception.class)
    public String manejarErroresGenerales(Exception ex, Model model) {
        model.addAttribute("mensaje", "Ocurrió un error inesperado.");
        return "error";
    }
}
