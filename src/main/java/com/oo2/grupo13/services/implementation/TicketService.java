package com.oo2.grupo13.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo13.dtos.TicketDTOCliente;
import com.oo2.grupo13.dtos.TicketDTOSoporte;
import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.services.ITicketService;
import com.oo2.grupo13.repositories.ITicketRepository;
import org.modelmapper.ModelMapper;

@Service("ticketService")
public class TicketService implements ITicketService {
    private ITicketRepository ticketRepository;
    private ModelMapper modelMapper;

    public TicketService(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getAll() {
       return ticketRepository.findAll();
    }
    
    @Override
    public TicketDTOCliente insertOrUpdateCliente(TicketDTOCliente ticketModel) {
        Ticket ticket = ticketRepository.save(modelMapper.map(ticketModel, Ticket.class));
		return modelMapper.map(ticket, TicketDTOCliente.class);
    }

    @Override
    public TicketDTOSoporte insertOrUpdateSoporte(TicketDTOSoporte ticketModel) {
        Ticket ticket = ticketRepository.save(modelMapper.map(ticketModel, Ticket.class));
		return modelMapper.map(ticket, TicketDTOSoporte.class);
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
