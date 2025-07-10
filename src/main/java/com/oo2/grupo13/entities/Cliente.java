package com.oo2.grupo13.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@SuperBuilder 

public class Cliente extends Usuario {
	
	@ManyToMany
	@JoinTable(
		name = "cliente_area",
	    joinColumns = @JoinColumn(name = "cliente_id"),
	    inverseJoinColumns = @JoinColumn(name = "area_id")
	)
	
	private Set<Area> areas = new HashSet<>();

	
}
