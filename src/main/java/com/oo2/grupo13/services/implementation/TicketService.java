package com.oo2.grupo13.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oo2.grupo13.dtos.TicketDTOCliente;
import com.oo2.grupo13.dtos.TicketDTOSoporte;
import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.ESTADO;
import com.oo2.grupo13.entities.PRIORIDAD;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.services.ITicketService;

import com.oo2.grupo13.repositories.ITicketRepository;
import com.oo2.grupo13.repositories.IUsuarioRepository;

import org.modelmapper.ModelMapper;

@Service("ticketService")
public class TicketService implements ITicketService {
    private ITicketRepository ticketRepository;
    private IUsuarioRepository usuarioRepository;
    private ModelMapper modelMapper;

    public TicketService(ITicketRepository ticketRepository, IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getAll() {  
       return ticketRepository.findAll();
    }
    
    @Override
    public TicketDTOCliente insertOrUpdateCliente(TicketDTOCliente ticketModel) {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
        }

        Ticket ticket = new Ticket(ticketModel.getDescripcion(), 
                                   ticketModel.getAsunto(),
                                   null, // fechaAlta will be set by the repository
                                   PRIORIDAD.MEDIA,
                                   ESTADO.NUEVO, 
                                   (Cliente) usuarioRepository.findByEmail(ticketModel.getCliente()).get(), 
                                   null);

        
        ticket = ticketRepository.save(ticket);
        // Map the saved ticket back to DTO
		return modelMapper.map(ticket, TicketDTOCliente.class);
    }

    @Override
    public TicketDTOSoporte insertOrUpdateSoporte(TicketDTOSoporte ticketModel) {
        if(ticketModel.getEstado() == ESTADO.COMPLETADO){
            List<Tarea> tareasNoCompletadas = ticketModel.getTareas().stream()
                .filter(t -> !t.isCompletada())
                .toList();
            if(tareasNoCompletadas.size()>0){
                throw new com.oo2.grupo13.exceptions.TareasSinCompletarException("No se puede completar el ticket si quedan tareas pendientes.");
            }
            ticketModel.setFechaBaja(java.time.LocalDateTime.now());
        } else {
            ticketModel.setFechaBaja(null);
        }


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
    @Override
    public Ticket findById(long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Optional<TicketDTOSoporte> findByIdSoporte(long id) {
       Ticket ticket = ticketRepository.findById(id);
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
        }
        if (ticket != null) {
            TicketDTOSoporte ticketDTO = modelMapper.map(ticket, TicketDTOSoporte.class);
            return Optional.of(ticketDTO);
        }
        return Optional.empty();
    }

}

