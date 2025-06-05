package com.oo2.grupo13.dtos;

import com.oo2.grupo13.enums.ROL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsuarioRolDTO {
	private int id;
	private ROL rol;
}
