package com.oo2.grupo13.repositories;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.TURNO;

public interface ISoporteRepository {
	Optional<Soporte> findByCuil(String cuil);
	List<Soporte> findByEspecialidades_Id(Long especialidadId);
	List<Soporte> findByEspecialidades_Nombre(String nombreEspecialidad);
	List<Soporte> findByTurno(TURNO turno);
	List<Soporte> findByFechaIngresoAfter(LocalDate fecha);

}
