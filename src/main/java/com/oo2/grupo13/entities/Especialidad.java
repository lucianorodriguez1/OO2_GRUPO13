package com.oo2.grupo13.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Especialidad {

	@ManyToMany(mappedBy = "especialidades")
	private List<Soporte> soportes = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public Especialidad(String nombre) {
		this.nombre = nombre;
	}

}
