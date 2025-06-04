package com.oo2.grupo13.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Tarea;
import java.util.Optional;
import java.util.List;


public interface ITareaRepository extends JpaRepository<Tarea, Serializable> {

    public abstract Optional<Tarea> findByNombre(String nombre);

    public abstract List<Tarea> findByTicketId(long ticketId);

    public abstract List<Tarea> findBySoporte(Soporte soporte);

    public abstract List<Tarea> findByCompletada(boolean completada);

    public abstract List<Tarea> findByTicketIdAndCompletada(long ticketId, boolean completada);

    public abstract List<Tarea> findBySoporteIdAndCompletada(long soporteId, boolean completada);

}
