package com.oo2.grupo13.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo13.entities.Ticket;

@Repository("ticketRepository")
public interface ITicketRepository extends JpaRepository<Ticket, Serializable> {
    public abstract Ticket findById(long id);
    public abstract Ticket findByAsunto(String asunto);

    @Query("SELECT t FROM Ticket t WHERE t.cliente.id = :clienteId")
    public abstract List<Ticket> findByClienteId(@Param("clienteId") long clienteId);
    List<Ticket> findBySoporteAsignado_Id(long soporteId);
}
