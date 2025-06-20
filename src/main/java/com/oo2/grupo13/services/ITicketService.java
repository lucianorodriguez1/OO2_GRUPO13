package com.oo2.grupo13.services;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo13.dtos.TicketDTOCliente;
import com.oo2.grupo13.dtos.TicketDTOSoporte;
import com.oo2.grupo13.entities.Ticket;

public interface ITicketService {
    public List<Ticket> getAll();
    public List<Ticket> getAllByUserId(long id);
    public Ticket findById(long id);
    public Optional<TicketDTOSoporte> findByIdSoporte(long id);
    public TicketDTOCliente insertOrUpdateCliente(TicketDTOCliente ticketModel);
    public TicketDTOSoporte insertOrUpdateSoporte(TicketDTOSoporte ticketModel);
    public boolean remove(long id);
}
