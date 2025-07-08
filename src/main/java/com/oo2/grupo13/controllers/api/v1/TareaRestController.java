package com.oo2.grupo13.controllers.api.v1;

import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.services.ITareaService;
import com.oo2.grupo13.services.ITicketService;

import jakarta.validation.constraints.NotBlank;

import com.oo2.grupo13.entities.Ticket;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/tareas")
public class TareaRestController {

    private ITareaService tareaService;
    private ITicketService ticketService;

    private ModelMapper modelMapper = new ModelMapper();
    public TareaRestController(ITareaService tareaService, ITicketService ticketService) {
        this.tareaService = tareaService;
        this.ticketService = ticketService;
    }

// Lista de tareas por ticket
    @GetMapping(value = "/porTicket/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TareaDTO>> getAllTareasTicket(@RequestParam(name = "ticketId", required = false) Long ticketId) {
        Ticket ticket = null;
        if (ticketId != null) {
            ticket = ticketService.findById(ticketId);
            if (ticket == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        List<Tarea> tareas = tareaService.filtrarPorTicket(ticket);
        List<TareaDTO> tareaDTOs = tareas.stream()
                .map(tarea -> modelMapper.map(tarea, TareaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(tareaDTOs, HttpStatus.OK);
    }

// Tarea especifica por id
    @GetMapping(value = "/porId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TareaDTO> getTareaById(@RequestParam("id") long id) {
        TareaDTO tarea = tareaService.findById(id).orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + id));
        TareaDTO tareaDTO = modelMapper.map(tarea, TareaDTO.class);
        return new ResponseEntity<>(tareaDTO, HttpStatus.OK);
    }

//Crear una nueva tarea
    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TareaDTO> createTarea(@RequestBody TareaDTORest tareaDTORest) {
        TareaDTO tareaDTO = new TareaDTO();
        tareaDTO.setDescripcion(tareaDTORest.descripcion());    
        tareaDTO.setNombre(tareaDTORest.nombre());
        tareaDTO.setCompletada(tareaDTORest.completada());
        tareaDTO.setIdSoporte(tareaDTORest.idSoporte());
        tareaDTO.setSoporte(tareaDTORest.soporte());
        tareaDTO.setIdTicket(tareaDTORest.idTicket());
        tareaDTO.setAsuntoTicket(tareaDTORest.asuntoTicket());

        TareaDTO crearTarea = modelMapper.map(tareaService.insertOrUpdate(tareaDTO), TareaDTO.class);
        return new ResponseEntity<>(crearTarea, HttpStatus.CREATED);
    }   
    
    public record TareaDTORest( long id, @NotBlank String descripcion, @NotBlank String nombre, boolean completada, long idSoporte, String soporte, long idTicket, String asuntoTicket) {}
}