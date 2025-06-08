package com.oo2.grupo13.dtos;

import com.oo2.grupo13.entities.UsuarioRol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
public class UsuarioDTO {
	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private String fotoPerfil;
	private UsuarioRol rol; 
}
