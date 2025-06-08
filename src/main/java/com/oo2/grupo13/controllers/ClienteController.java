package com.oo2.grupo13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo13.dtos.ClienteDTO;
import com.oo2.grupo13.dtos.ClienteEditarDTO;
import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.helpers.ViewRouterHelper;
import com.oo2.grupo13.services.implementation.ClienteService;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/nuevo")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouterHelper.CLIENTE_CREAR_FORM);
		mAV.addObject("cliente", new Cliente());
		return mAV;
	}
	
	@PostMapping("/guardar")
	public ModelAndView create(@Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView(ViewRouterHelper.CLIENTE_CREAR_FORM);
		}

		// Convertir DTO a entidad Cliente y guardar
		Cliente cliente = new Cliente();
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setApellido(clienteDTO.getApellido());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setPassword(clienteDTO.getPassword());
		cliente.setFotoPerfil(clienteDTO.getFotoPerfil());
		cliente.setRol(clienteDTO.getRol());

		clienteService.crearOActualizarCliente(cliente);

		return new ModelAndView("redirect:/usuario");
	}
	
	@PostMapping("/actualizar/{id}")
	public ModelAndView update(@PathVariable int id, @Valid @ModelAttribute("cliente") ClienteEditarDTO clienteDTO, BindingResult result) {
	    if (result.hasErrors()) {
	        ModelAndView mAV = new ModelAndView(ViewRouterHelper.CLIENTE_EDITAR_FORM);
	        mAV.addObject("cliente", clienteDTO);
	        return mAV;
	    }

	    Cliente existente = clienteService.findById(id);
	    if (existente == null) {
	        return new ModelAndView("redirect:/usuario"); // cliente no encontrado
	    }

	    // Actualizamos los datos
	    existente.setNombre(clienteDTO.getNombre());
	    existente.setApellido(clienteDTO.getApellido());
	    existente.setEmail(clienteDTO.getEmail());
	    existente.setFotoPerfil(clienteDTO.getFotoPerfil());
	    existente.setRol(clienteDTO.getRol());

	    clienteService.crearOActualizarCliente(existente);

	    return new ModelAndView("redirect:/usuario");
	}
	 

}

