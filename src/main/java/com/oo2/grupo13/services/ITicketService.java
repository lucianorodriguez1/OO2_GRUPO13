package com.oo2.grupo13.services;

import java.util.List;

import com.oo2.grupo13.dto.TicketDTO;
import com.oo2.grupo13.entities.Ticket;

public interface ITicketService {
    public List<Ticket> getAll();
    public TicketDTO insertOrUpdate(TicketDTO ticketModel);
    public boolean remove(long id);
}
