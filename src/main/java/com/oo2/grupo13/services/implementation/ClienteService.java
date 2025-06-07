package com.oo2.grupo13.services.implementation;

import org.springframework.stereotype.Service;

import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.UsuarioRol;
import com.oo2.grupo13.enums.ROL;
import com.oo2.grupo13.exceptions.EmailYaExisteException;
import com.oo2.grupo13.repositories.IClienteRepository;
import com.oo2.grupo13.repositories.IUsuarioRolRepository;

@Service
public class ClienteService {
	
	private IClienteRepository clienteRepository;
	
	private IUsuarioRolRepository usuarioRolRepository;
	
    private UsuarioService usuarioService;
	
	public ClienteService(IClienteRepository clienteRepository, IUsuarioRolRepository usuarioRolRepository, UsuarioService usuarioService) {
		this.clienteRepository = clienteRepository;
		this.usuarioRolRepository = usuarioRolRepository;
		this.usuarioService = usuarioService;
	}

	public void crearCliente(Cliente cliente) {
	    // Asignar el rol USUARIO
	    UsuarioRol rolUsuario = usuarioRolRepository.findByRol(ROL.USUARIO)
	            .orElseThrow();
	    cliente.setRol(rolUsuario);
	    usuarioService.validarEmailUnico(cliente.getEmail());
	    clienteRepository.save(cliente);
	}
	
}