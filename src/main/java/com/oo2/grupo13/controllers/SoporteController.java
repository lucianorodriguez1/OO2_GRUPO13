package com.oo2.grupo13.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo13.dtos.SoporteDTO;
import com.oo2.grupo13.services.IEmailService;
import com.oo2.grupo13.services.ISoporteService;


@Controller
@RequestMapping("/soporte")
public class SoporteController {
	private ISoporteService soporteService;
    private IEmailService emailService;

	public SoporteController(ISoporteService soporteService, IEmailService emailService) {
		this.soporteService = soporteService;
        this.emailService = emailService;

	}
	
	@GetMapping("/lista")
	public ModelAndView listarSoportes() {
	    ModelAndView mAV = new ModelAndView("soporte/lista"); // nombre de la vista
	    mAV.addObject("soportes", soporteService.getAll());
	    return mAV;
	}

	
	@GetMapping("/nuevo")
	public ModelAndView nuevoSoporte() {
		ModelAndView mAV = new ModelAndView("soporte/nuevo");
		mAV.addObject("soporte", new SoporteDTO());
		return mAV;
	}

	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("soporte") SoporteDTO soporteDto, RedirectAttributes redirectAttributes) {
		/*
	    System.out.println("Nombre: " + soporteDto.getNombre());
	    System.out.println("Apellido: " + soporteDto.getApellido());
	    System.out.println("Email: " + soporteDto.getEmail());
	    System.out.println("Password: " + soporteDto.getPassword());
	    System.out.println("Foto Perfil: " + soporteDto.getFotoPerfil());
	    System.out.println("CUIL: " + soporteDto.getCuil());
	    System.out.println("Fecha Ingreso: " + soporteDto.getFechaIngreso());
	    System.out.println("Turno: " + soporteDto.getTurno());
	    System.out.println("Rol (ENUM): " + (soporteDto.getRol() != null ? soporteDto.getRol().getRol() : "rol es null"));
		*/
	    SoporteDTO soporteGuardado = soporteService.insertOrUpdate(soporteDto);

        String[] destinatario = new String[] { soporteGuardado.getEmail() };
        String asunto = "Registro exitoso como soporte";
        String mensaje = "Hola " + soporteGuardado.getNombre() + ", ya estás registrado en el sistema como soporte.";

        try {
            emailService.sendEmail(destinatario, asunto, mensaje);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
        
        
        redirectAttributes.addFlashAttribute("mensajeCrear", "Soporte creado exitosamente.");

        return new RedirectView("/usuario/index");

	}
	
	@PostMapping("/actualizar/{id}")
	public RedirectView actualizarSoporte(@PathVariable("id") int id,
	                                      @ModelAttribute("soporte") SoporteDTO soporteDto,
	                                      RedirectAttributes redirectAttributes) {
	    // Asegurar que el ID sea el correcto
	    soporteDto.setId(id);

	    soporteService.insertOrUpdate(soporteDto);
	    
	    redirectAttributes.addFlashAttribute("mensajeEditar", "Soporte actualizado correctamente.");

	    return new RedirectView("/usuario/index");
	}

	
}
