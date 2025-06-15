package com.oo2.grupo13.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder 
@Getter @Setter @NoArgsConstructor @AllArgsConstructor

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
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
