package com.oo2.grupo13.repositories;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.entities.Ticket;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ITareaRepository extends JpaRepository<Tarea, Serializable> {

    public abstract List<Tarea> findAll();
    public abstract Optional<Tarea> findById(Long id);
    public abstract Optional<Tarea> findByNombre(String nombre);
    public abstract List<Tarea> findByCompletada(boolean completada);
    public abstract List<Tarea> findByTicketAsociado(Ticket ticketAsociado);
    public abstract List<Tarea> findByTicketAsociadoAndCompletada(Ticket ticket, Boolean completada);

}
