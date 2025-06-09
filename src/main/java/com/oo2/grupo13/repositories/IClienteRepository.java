package com.oo2.grupo13.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.UsuarioRol;

@Repository("clienteRepository")
public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
	
	boolean existsByEmail(String email);

}
