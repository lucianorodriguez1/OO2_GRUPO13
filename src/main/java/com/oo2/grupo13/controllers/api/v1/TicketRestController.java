package com.oo2.grupo13.controllers.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.oo2.grupo13.dtos.TicketDTOCliente;
import com.oo2.grupo13.dtos.TicketDTOSoporte;
import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.services.ITicketService;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketRestController {

    private ITicketService ticketService;
    private ModelMapper modelMapper = new ModelMapper();

    public TicketRestController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TicketDTOCliente>> index(){
        List<Ticket> tickets = ticketService.getAll();
        List<TicketDTOCliente> ticketDTOs = new ArrayList<>();

        for(Ticket ticket : tickets) {
            TicketDTOCliente ticketDTO = modelMapper.map(ticket, TicketDTOCliente.class);
            ticketDTO.setCliente(ticket.getCliente().getEmail());
            if (ticket.getSoporteAsignado() != null) {
                ticketDTO.setSoporteAsignado(ticket.getSoporteAsignado().getEmail());
            } else {
                ticketDTO.setSoporteAsignado("No asignado");
            }
            ticketDTOs.add(ticketDTO);
        }
        return new ResponseEntity<List<TicketDTOCliente>>(ticketDTOs, HttpStatus.OK);
    }
    
}
