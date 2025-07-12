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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/api/v1/tickets")
public class TicketRestController {

    private ITicketService ticketService;
    private ModelMapper modelMapper = new ModelMapper();

    public TicketRestController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtener todos los tickets de la base de datos", description = "Permite obtener una lista de JSON con todos los tickets subidos en la base de datos.")
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Lista de tickets", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					[
					              {
                                        "id": 1,
                                        "descripcion": "descripcion 1",
                                        "asunto": "Ticket 1",
                                        "fechaAlta": "2025-06-16T23:31:14.472283",
                                        "fechaBaja": "2025-06-19T18:02:08.442305",
                                        "estado": "COMPLETADO",
                                        "cliente": "cliente@gmail.com",
                                        "soporteAsignado": "soporte@gmail.com"
                                    }
					                
					 ]""")))})
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
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Ticket creado correctamente", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					[
					              {
                                        "id": 1,
                                        "descripcion": "descripcion 1",
                                        "asunto": "Ticket 1",
                                        "fechaAlta": "2025-06-16T23:31:14.472283",
                                        "fechaBaja": "2025-06-19T18:02:08.442305",
                                        "estado": "COMPLETADO",
                                        "cliente": "cliente@gmail.com",
                                        "soporteAsignado": "soporte@gmail.com"
                                    }
					                
					 ]""")))})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDTOCliente> crear(@RequestBody TicketDTORest ticketRecord) {
        TicketDTOCliente ticketDTO = new TicketDTOCliente();
        ticketDTO.setDescripcion(ticketRecord.descripcion());
        ticketDTO.setAsunto(ticketRecord.asunto());
        ticketDTO.setCliente(ticketRecord.mailCliente());
        
        TicketDTOCliente ticketDTOMuestra = ticketService.insertOrUpdateCliente(ticketDTO);
        ticketDTOMuestra.setCliente(ticketRecord.mailCliente);

        return new ResponseEntity<>(ticketDTOMuestra, HttpStatus.CREATED);
    }
    
    public record TicketDTORest(@NotBlank String descripcion, @NotBlank String asunto, @Email @NotBlank String mailCliente) {}
}
