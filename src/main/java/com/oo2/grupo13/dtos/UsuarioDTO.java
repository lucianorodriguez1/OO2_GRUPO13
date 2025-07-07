package com.oo2.grupo13.dtos;

import com.oo2.grupo13.entities.UsuarioRol;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsuarioDTO {
	@Schema(hidden = true)
	private int id;
	
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@NotBlank(message = "El apellido es obligatorio")
	private String apellido;
	public UsuarioDTO(String nombre, String apellido, String email, String password, String fotoPerfil, UsuarioRol rol) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.fotoPerfil = fotoPerfil;
		this.rol = rol;
	}

	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El email no es válido")
	private String email;

	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password;

	private String fotoPerfil;
	
	@Schema(hidden = true)
	private UsuarioRol rol;
	
	
}
