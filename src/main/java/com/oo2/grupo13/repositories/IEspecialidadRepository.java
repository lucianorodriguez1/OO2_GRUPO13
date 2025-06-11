package com.oo2.grupo13.repositories;

import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.oo2.grupo13.entities.Especialidad;

@Repository("especialidadRepository")
public interface IEspecialidadRepository extends JpaRepository<Especialidad, Serializable>{
	Optional<Especialidad> findByNombre(String nombre);
	boolean existsByNombre(String nombre);

}
