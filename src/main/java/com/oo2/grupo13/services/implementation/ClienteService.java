package com.oo2.grupo13.services.implementation;

import java.text.MessageFormat;
import org.springframework.stereotype.Service;
import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.UsuarioRol;
import com.oo2.grupo13.enums.ROL;
import com.oo2.grupo13.repositories.IClienteRepository;
import com.oo2.grupo13.repositories.IUsuarioRolRepository;

import jakarta.persistence.EntityNotFoundException;

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


	public void crearOActualizarCliente(Cliente cliente) {
	    // Asignar el rol USUARIO
	    UsuarioRol rolUsuario = usuarioRolRepository.findByRol(ROL.CLIENTE)
	            .orElseThrow();
	    cliente.setRol(rolUsuario);
	    usuarioService.validarEmailUnico(cliente.getEmail(), cliente.getId());
	    clienteRepository.save(cliente);
	}

	public Cliente findById(int id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Cliente con id {0} no encontrado",id)));
		return cliente;
	}

}
