package com.oo2.grupo13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.services.implementation.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/nuevo")
	public ModelAndView crear() {
		ModelAndView mAV = new ModelAndView("cliente/nuevo");
		mAV.addObject("cliente", new Cliente());
		return mAV;
	}
	
	@PostMapping("/guardar")
	public ModelAndView create(@ModelAttribute Cliente cliente) {
		ModelAndView mAV = new ModelAndView("cliente/nuevo");
	    clienteService.crearCliente(cliente);
	    return mAV;
	}


}