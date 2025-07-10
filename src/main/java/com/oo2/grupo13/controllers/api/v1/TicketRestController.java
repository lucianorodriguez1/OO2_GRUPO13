package com.oo2.grupo13.controllers.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import com.oo2.grupo13.dtos.TicketDTOCliente;
import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.services.ITicketService;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/tickets")
public class TicketRestController {

    private ITicketService ticketService;
    private ModelMapper modelMapper = new ModelMapper();

    public TicketRestController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }
    @PreAuthorize("hasRole('ADMIN')")
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

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDTOCliente> crear(@RequestBody TicketDTORest ticketRecord) {
        TicketDTOCliente ticketDTO = new TicketDTOCliente();
        ticketDTO.setDescripcion(ticketRecord.descripcion());
        ticketDTO.setAsunto(ticketRecord.asunto());
        ticketDTO.setCliente(ticketRecord.mailCliente());
        
        return new ResponseEntity<>(ticketService.insertOrUpdateCliente(ticketDTO), HttpStatus.CREATED);
    }
    
    public record TicketDTORest(@NotBlank String descripcion, @NotBlank String asunto, @Email @NotBlank String mailCliente) {}
}
