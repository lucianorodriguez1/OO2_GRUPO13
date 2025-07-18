package com.oo2.grupo13.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

//@Builder
public class Soporte extends Usuario {
	
	@ManyToMany
	@JoinTable(name = "soporte_especialidad", joinColumns = @JoinColumn(name = "soporte_id"), inverseJoinColumns = @JoinColumn(name = "especialidad_id"))
	
	private List<Especialidad> especialidades;
	private String cuil;
	private LocalDate fechaIngreso;
	private TURNO turno;

	
	public Soporte(String nombre, String apellido, String email, String password, String fotoPerfil, UsuarioRol rol,
			String cuil, LocalDate fechaIngreso, TURNO turno) {
		super(nombre, apellido, email, password, fotoPerfil, rol);
		this.especialidades = new ArrayList<>();
		this.cuil = cuil;
		this.fechaIngreso = fechaIngreso;
		this.turno = turno;
	}
	

}
