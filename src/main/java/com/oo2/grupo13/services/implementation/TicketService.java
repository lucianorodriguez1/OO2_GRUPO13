package com.oo2.grupo13.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo13.dto.TicketDTO;
import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.services.ITicketService;
import com.oo2.grupo13.repositories.ITicketRepository;
@Service("ticketService")
public class TicketService implements ITicketService {
    private ITicketRepository ticketRepository;
    //private ModelMapper ModelMapper;

    public TicketService(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getAll() {
       return ticketRepository.findAll();
    }

    @Override
    public TicketDTO insertOrUpdate(TicketDTO ticketModel) {
        return null;
    }

    @Override
    public boolean remove(long id) {
      try {
            ticketRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
