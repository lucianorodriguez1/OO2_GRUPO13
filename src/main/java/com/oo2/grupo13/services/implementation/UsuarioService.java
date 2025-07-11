package com.oo2.grupo13.services.implementation;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oo2.grupo13.entities.Usuario;
import com.oo2.grupo13.exceptions.EmailYaExisteException;
import com.oo2.grupo13.exceptions.UsuarioNoEncontradoException;
import com.oo2.grupo13.repositories.IUsuarioRepository;
import com.oo2.grupo13.services.IUsuarioService;

import jakarta.persistence.EntityNotFoundException;

@Service("usuarioService")
public class UsuarioService implements UserDetailsService, IUsuarioService{
	
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
	    return usuarioRepository.findById(id)
	        .orElseThrow(() -> new UsuarioNoEncontradoException("No se encontró el usuario con ID: " + id));
	}

	@Override
    @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		  return usuarioRepository.findByEmail(username).orElseThrow(
	                () -> new UsernameNotFoundException(MessageFormat.format("User with email {0} not found", username))
	        );
	}

	@Override
	public String authenticate(String email, String password) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException(MessageFormat.format("Usuario " + email + "no encontrado", email));
		}
		
		if (!encoder.matches(password, usuario.get().getPassword())) {
			throw new EntityNotFoundException("Contraseña incorrecta");
		}
		
		return "Usuario autenticado correctamente";
	}
	

}
