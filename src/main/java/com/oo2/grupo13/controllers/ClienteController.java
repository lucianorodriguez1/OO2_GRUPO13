package com.oo2.grupo13.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oo2.grupo13.dtos.ClienteDTO;
import com.oo2.grupo13.dtos.ClienteEditarDTO;
import com.oo2.grupo13.entities.Area;
import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.helpers.ViewRouteHelper;
import com.oo2.grupo13.services.implementation.AreaService;
import com.oo2.grupo13.services.implementation.ClienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	private ClienteService clienteService;
	private AreaService areaService;
	
	public ClienteController(ClienteService clienteService, AreaService areaService) {
		this.clienteService = clienteService;
		this.areaService = areaService;
	}
	
	@GetMapping("/nuevo")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_CREAR_FORM);
		mAV.addObject("cliente", new ClienteDTO());
		mAV.addObject("areas", areaService.findAll());
		return mAV;
	}
	
	@PostMapping("/guardar")
	public ModelAndView create(@Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_CREAR_FORM);
			mAV.addObject("areas", areaService.findAll());
			return mAV;
		}

		// Convertir DTO a entidad Cliente y guardar
		Cliente cliente = new Cliente();
		
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setApellido(clienteDTO.getApellido());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setPassword(new BCryptPasswordEncoder(7).encode(clienteDTO.getPassword())); //mala practica totalmente, solución temporal hasta encontrar una mejor
		cliente.setFotoPerfil(clienteDTO.getFotoPerfil());
		cliente.setRol(clienteDTO.getRol());
		// Asignar áreas
		Set<Area> areasSeleccionadas = new HashSet<>(areaService.findByIds(clienteDTO.getAreaIds()));
		cliente.setAreas(areasSeleccionadas);
		
		clienteService.crearOActualizarCliente(cliente);
        redirectAttributes.addFlashAttribute("mensajeCrear", "Cliente creado exitosamente.");

		return new ModelAndView("redirect:/" + ViewRouteHelper.USUARIO_INDEX);
	}
	
	@PostMapping("/actualizar/{id}")
	public ModelAndView update(@PathVariable int id, @Valid @ModelAttribute("cliente") ClienteEditarDTO clienteDTO, BindingResult result, RedirectAttributes redirectAttributes) {
	    if (result.hasErrors()) {
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_EDITAR_FORM);
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
	    Set<Area> nuevasAreas = new HashSet<>(areaService.findByIds(clienteDTO.getAreaIds()));
	    existente.setAreas(nuevasAreas);
	    
	    clienteService.crearOActualizarCliente(existente);
        redirectAttributes.addFlashAttribute("mensajeEditar", "Cliente actualizado exitosamente.");

	    return new ModelAndView("redirect:/usuario/index");
	}
	 

}


