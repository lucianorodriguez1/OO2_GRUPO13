package com.oo2.grupo13.services.implementation;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo13.entities.Usuario;
import com.oo2.grupo13.exceptions.EmailYaExisteException;
import com.oo2.grupo13.repositories.IUsuarioRepository;

import jakarta.persistence.EntityNotFoundException;


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

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}
	
	public boolean delete(int id) {
		try {
			usuarioRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Usuario findById(int id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Usuario con id {0} no encontrado",id)));
		return usuario;
	}
	

}
