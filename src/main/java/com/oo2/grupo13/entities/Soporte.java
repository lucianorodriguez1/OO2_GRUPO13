package com.oo2.grupo13.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
public class Soporte extends Usuario {
	
	@ManyToMany
	@JoinTable(name = "soporte_especialidad", joinColumns = @JoinColumn(name = "soporte_id"), inverseJoinColumns = @JoinColumn(name = "especialidad_id"))
	
	private List<Especialidad> especialidades;
	private String cuil;
	private LocalDate fechaIngreso;
	private TURNO turno;
	
	public Soporte() {
		super();
	}
	
	public Soporte(String nombre, String apellido, String email, String password, String fotoPerfil, UsuarioRol rol,
			String cuil, LocalDate fechaIngreso, TURNO turno) {
		super(nombre, apellido, email, password, fotoPerfil, rol);
		this.especialidades = new ArrayList<>();
		this.cuil = cuil;
		this.fechaIngreso = fechaIngreso;
		this.turno = turno;
	}
	
	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public TURNO getTurno() {
		return turno;
	}

	public void setTurno(TURNO turno) {
		this.turno = turno;
	}

}
