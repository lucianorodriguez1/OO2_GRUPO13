package com.oo2.grupo13.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.oo2.grupo13.dtos.ErrorResponseDTO;
import com.oo2.grupo13.dtos.SoporteDTO;
import com.oo2.grupo13.dtos.TicketDTOSoporte;
import com.oo2.grupo13.helpers.ViewRouteHelper;
import com.oo2.grupo13.services.ISoporteService;
import com.oo2.grupo13.services.ITicketService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;


@ControllerAdvice
public class HandlerExceptions {
	@Autowired
    private ITicketService ticketService;

    @Autowired
    private ISoporteService soporteService;

	@ExceptionHandler(EmailYaExisteException.class)
	public ModelAndView manejarEmailYaExiste(EmailYaExisteException ex, HttpServletRequest request) {
	    ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMAIL_EXISTE_ERROR);
	    mAV.addObject("mensajeError", ex.getMessage());

	    // Determinar desde donde vino la solicitud
	    String referer = request.getHeader("Referer");
	    
	    if (referer != null) {
	        if (referer.contains("/cliente/editar/")) {
	            mAV.addObject("volverUrl", referer); // volver al form de edicion cliente
	        } else if (referer.contains("/soporte/editar/")) {
	            mAV.addObject("volverUrl", referer); // volver al form de edicion soporte
	        } else if (referer.contains("/cliente/nuevo")) {
	            mAV.addObject("volverUrl", "/cliente/nuevo"); // volver a crear cliente
	        } else if (referer.contains("/soporte/nuevo")) {
	            mAV.addObject("volverUrl", "/soporte/nuevo"); // volver a crear soporte
	        } else {
	            // fallback genérico
	            mAV.addObject("volverUrl", "/"); 
	        }
	    } else {
	        // fallback si no hay referer
	        mAV.addObject("volverUrl", "/");
	    }

	    return mAV;
	}

	
    /*@ExceptionHandler(TareaNoEncontradaException.class)
    public ModelAndView manejarTareaNoEncontrada(TareaNoEncontradaException ex) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.TAREA_NO_ENCONTRADA_ERROR);
    	mAV.addObject("mensaje", ex.getMessage());
        return mAV;
    }*/

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
    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ModelAndView manejarUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
        ModelAndView mav = new ModelAndView("error/usuarioNoEncontrado");
        mav.addObject("mensajeError", ex.getMessage());
        mav.addObject("volverUrl", "/usuario/index");
        return mav;
    }

	@ExceptionHandler(TareasSinCompletarException.class)
	public ModelAndView manejarTareasSinCompletar(TareasSinCompletarException ex,
        HttpServletRequest request) {
		String ticketId = request.getParameter("id");


		TicketDTOSoporte ticket = ticketService.findByIdSoporte(Long.parseLong(ticketId)).get();
		List<SoporteDTO> soportes = soporteService.getAll();

        ModelAndView mAV = new ModelAndView("ticket/editar_ticket");
        mAV.addObject("ticket", ticket);
        mAV.addObject("soportes", soportes);
        mAV.addObject("errorTareasSinCompletar", true);
        mAV.addObject("mensaje", ex.getMessage());
        return mAV;
	}

	@ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDenied(AccessDeniedException ex) throws AccessDeniedException{
        ex.printStackTrace();
        throw ex;
    }
	//para manejar la excepcion de ticket no encontrado en api
	@ExceptionHandler(TicketNoExisteException.class)
    public ResponseEntity<ErrorResponseDTO> handleTicketNotFound(TicketNoExisteException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

	//para manejar la excepcion de tarea no encontrada en api
	@ExceptionHandler(TareaNoEncontradaException.class)
	public ResponseEntity<ErrorResponseDTO> handleTareaNotFound(TareaNoEncontradaException ex) {
    ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
