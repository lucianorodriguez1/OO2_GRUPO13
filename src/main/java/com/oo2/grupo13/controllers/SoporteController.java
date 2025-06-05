package com.oo2.grupo13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo13.dtos.SoporteDTO;
import com.oo2.grupo13.services.ISoporteService;

@Controller
@RequestMapping("/soporte")
public class SoporteController {
	private ISoporteService soporteService;

	public SoporteController(ISoporteService soporteService) {
		this.soporteService = soporteService;
	}

	@GetMapping("/nuevo")
	public ModelAndView nuevoSoporte() {
		ModelAndView mAV = new ModelAndView("soporte/nuevo");
		mAV.addObject("soporte", new SoporteDTO());
		return mAV;
	}

	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("soporte") SoporteDTO soporteDto) {
		soporteService.insertOrUpdate(soporteDto);
		return new RedirectView("/soporte/nuevo"); 
	}
}
