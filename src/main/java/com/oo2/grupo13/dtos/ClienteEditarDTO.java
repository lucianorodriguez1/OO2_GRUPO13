package com.oo2.grupo13.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ClienteEditarDTO extends UsuarioEditarDTO {
	private List<Integer> areaIds;
}
