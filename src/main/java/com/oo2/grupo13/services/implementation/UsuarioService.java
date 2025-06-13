package com.oo2.grupo13.services.implementation;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oo2.grupo13.entities.Usuario;
import com.oo2.grupo13.exceptions.EmailYaExisteException;
import com.oo2.grupo13.repositories.IUsuarioRepository;

import jakarta.persistence.EntityNotFoundException;


@Service("usuarioService")
public class UsuarioService implements UserDetailsService{
	
	private IUsuarioRepository usuarioRepository;

	public UsuarioService(IUsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	
	public void validarEmailUnico(String email, int idCliente) {
		Optional<Usuario> existente = usuarioRepository.findByEmail(email);
	    
	    if (existente.isPresent() && existente.get().getId() != idCliente) {
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

	@Override
    @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		  return usuarioRepository.findByEmail(username).orElseThrow(
	                () -> new UsernameNotFoundException(MessageFormat.format("User with email {0} not found", username))
	        );
	}
	

}
