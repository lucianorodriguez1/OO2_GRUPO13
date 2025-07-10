package com.oo2.grupo13.services;

import java.util.List;

import com.oo2.grupo13.dtos.ClienteDTO;
import com.oo2.grupo13.entities.Cliente;

public interface IClienteService {
	public void crearOActualizarCliente(Cliente cliente);
	public Cliente findById(int id);
	public List<ClienteDTO> getAll();
}

