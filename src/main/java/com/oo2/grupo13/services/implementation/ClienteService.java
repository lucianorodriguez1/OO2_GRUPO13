package com.oo2.grupo13.services.implementation;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo13.dtos.ClienteDTO;
import com.oo2.grupo13.entities.Area;
import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.UsuarioRol;
import com.oo2.grupo13.enums.ROL;
import com.oo2.grupo13.repositories.IClienteRepository;
import com.oo2.grupo13.repositories.IUsuarioRolRepository;
import com.oo2.grupo13.services.IClienteService;

import jakarta.persistence.EntityNotFoundException;

@Service("clienteService")
public class ClienteService implements IClienteService {
	
	private IClienteRepository clienteRepository;
	
	private IUsuarioRolRepository usuarioRolRepository;
	
    private UsuarioService usuarioService;
    
    private final ModelMapper modelMapper;

	public ClienteService(IClienteRepository clienteRepository, IUsuarioRolRepository usuarioRolRepository, UsuarioService usuarioService) {
		this.modelMapper = new ModelMapper();
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
	
	public List<ClienteDTO> getAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		
		return clientes.stream()
				.map(cliente -> {
					ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);
					dto.setAreaIds(
						cliente.getAreas().stream()
							.map(Area::getId)
							.collect(Collectors.toList())
					);
					return dto;
				})
				.collect(Collectors.toList());      
	}
	
}
