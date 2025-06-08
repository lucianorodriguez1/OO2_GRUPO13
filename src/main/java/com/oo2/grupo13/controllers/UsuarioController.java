package com.oo2.grupo13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo13.dtos.ClienteDTO;
import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Usuario;
import com.oo2.grupo13.helpers.ViewRouteHelper;
import com.oo2.grupo13.services.implementation.UsuarioService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USUARIO_INDEX);
		mAV.addObject("usuarios", usuarioService.getAll());
		return mAV;
	}
	
	@PostMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id) {
		usuarioService.delete(id);
		return "redirect:/usuario";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editarForm(@PathVariable int id) {
	    Usuario usuario = usuarioService.findById(id);
	    
	    if (usuario == null) {
	        return new ModelAndView("redirect:/usuario");
	    }

	    if (usuario instanceof Cliente cliente) {
	        ClienteDTO dto = new ClienteDTO();
	        dto.setId(cliente.getId());
	        dto.setNombre(cliente.getNombre());
	        dto.setApellido(cliente.getApellido());
	        dto.setEmail(cliente.getEmail());
	        dto.setPassword(cliente.getPassword());
	        dto.setFotoPerfil(cliente.getFotoPerfil());
	        dto.setRol(cliente.getRol());

	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_EDITAR_FORM);
	        mAV.addObject("cliente", dto);
	        return mAV;
	    }

//	    if (usuario instanceof Soporte soporte) {
//	        ModelAndView mAV = new ModelAndView(ViewRouterHelper.SOPORTE_EDITAR_FORM);
//	        mAV.addObject("soporte", soporte);
//	        return mAV;
//	    }

	    return new ModelAndView("redirect:/usuario");
	}


}
