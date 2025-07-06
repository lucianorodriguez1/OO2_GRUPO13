package com.oo2.grupo13.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oo2.grupo13.entities.Valoracion;

public interface IValoracionRepository extends JpaRepository <Valoracion, Serializable> {
    
    public abstract Optional<Valoracion> findById(long id);
    public abstract Optional<Valoracion> findByPuntaje(int puntaje);
    public abstract Optional<Valoracion> findByTicketId(Long ticketId);
}
