package com.oo2.grupo13.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsuarioDTO {
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String fotoPerfil;
	private String rol;
}
