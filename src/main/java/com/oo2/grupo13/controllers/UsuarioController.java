package com.oo2.grupo13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oo2.grupo13.dtos.ClienteDTO;
import com.oo2.grupo13.dtos.SoporteDTO;
import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Usuario;
import com.oo2.grupo13.helpers.ViewRouteHelper;
import com.oo2.grupo13.services.implementation.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USUARIO_INDEX);
		mAV.addObject("usuarios", usuarioService.getAll());
		return mAV;
	}
	
	@PostMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id, RedirectAttributes redirectAttributes) {
		usuarioService.delete(id);
		 redirectAttributes.addFlashAttribute("mensajeEliminar", "Usuario eliminado correctamente");
		    return "redirect:/usuario/index";
		}	
	
	@GetMapping("/editar/{id}")
	public ModelAndView editarForm(@PathVariable int id) {
	    Usuario usuario = usuarioService.findById(id);
	    
	    if (usuario == null) {
	        return new ModelAndView("redirect:/usuario/index");
	    } else if (usuario instanceof Cliente cliente) {
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
	    } else if (usuario instanceof Soporte soporte) {
	    	SoporteDTO dto = new SoporteDTO();
	    	dto.setId(soporte.getId());
	    	dto.setNombre(soporte.getNombre());
	    	dto.setApellido(soporte.getApellido());
	        dto.setEmail(soporte.getEmail());
	        dto.setPassword(soporte.getPassword());
	        dto.setFotoPerfil(soporte.getFotoPerfil());
	        dto.setCuil(soporte.getCuil());
	        dto.setFechaIngreso(soporte.getFechaIngreso());
	        dto.setTurno(soporte.getTurno());
	        dto.setRol(soporte.getRol());
	    	
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.SOPORTE_EDITAR_FORM);
	        mAV.addObject("soporte", dto);
	        return mAV;
	    }

	    return new ModelAndView("redirect:/usuario/index");
	}
	
	@GetMapping("ver/{id}")
	public ModelAndView verUsuario(@PathVariable int id) {
	    Usuario usuario = usuarioService.findById(id);

	    if (usuario == null) {
	        return new ModelAndView("redirect:/usuario/index");
	    } else if (usuario instanceof Cliente cliente) {
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.VER_CLIENTE);
	        mAV.addObject("cliente", cliente);
	        return mAV;
	    } else if (usuario instanceof Soporte soporte) {
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.VER_SOPORTE);
	        mAV.addObject("soporte", soporte);
	        return mAV;
	    }

	    return new ModelAndView("redirect:/usuario/index");
	}



}
