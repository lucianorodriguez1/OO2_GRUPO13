package com.oo2.grupo13.services;

import java.util.List;

import com.oo2.grupo13.dtos.TicketDTOCliente;
import com.oo2.grupo13.dtos.TicketDTOSoporte;
import com.oo2.grupo13.entities.Ticket;

public interface ITicketService {
    public List<Ticket> getAll();
    public TicketDTOCliente insertOrUpdateCliente(TicketDTOCliente ticketModel);
    public TicketDTOSoporte insertOrUpdateSoporte(TicketDTOSoporte ticketModel);
    public boolean remove(long id);
}
