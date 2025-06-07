package com.oo2.grupo13.services.implementation;

import org.springframework.stereotype.Service;

import com.oo2.grupo13.exceptions.EmailYaExisteException;
import com.oo2.grupo13.repositories.IUsuarioRepository;


@Service("usuarioService")
public class UsuarioService {
	
	private IUsuarioRepository usuarioRepository;

	public UsuarioService(IUsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	
	public void validarEmailUnico(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailYaExisteException("Ya existe un usuario registrado con el email: " + email);
        }
    }
}
