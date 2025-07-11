package com.oo2.grupo13.controllers.api.v1;

import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.services.ITareaService;
import com.oo2.grupo13.services.ITicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.exceptions.TareaNoEncontradaException;
import com.oo2.grupo13.exceptions.TicketNoExisteException;

import java.util.List;
import java.util.Optional;
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
    @Operation(summary = "Obtener las tareas de un ticket especifico", description = "Permite obtener la lista de tareas del ticket especificado por su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Lista de tareas", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					[
					              {
					                "id": 1,
					                "descripcion": "ejemplo de tarea",
					                "nombre": "Tarea de ejemplo",
					                "completada": "false",
					                "idSoporte": 1,
					                "soporte": "Juan Perez",
					                "idTicket": "1",
					                "asuntoTicket": "ejemplo de ticket",
					                
					 ]"""))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Ticket no encontrado", 
                content = @Content(mediaType = "application/json", 
                    examples = @ExampleObject(value = """
                        {
                            "error": "El ticket con ID 1 no existe."
                        }"""))
            ),
            @ApiResponse(responseCode = "500", description = "ERROR - Internal server error", 
                content = @Content(mediaType = "application/json", 
                    examples = @ExampleObject(value = """
                        {
                            "error": "Ocurrió un error inesperado al obtener las tareas"
                        }"""))
            )})
    public ResponseEntity<List<TareaDTO>> getAllTareasTicket(@RequestParam(name = "ticketId", required = false) Long ticketId) {
        Ticket ticket = null;
        if (ticketId != null) {
            ticket = ticketService.findById(ticketId);
            if (ticket == null) {
                throw new TicketNoExisteException(ticketId);
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
    @Operation(summary = "Obtener una tarea especifica mediante la busqueda del ID", description = "Permite obtener una tarea especifica")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Tarea", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					[
					              {
					                "id": 1,
					                "descripcion": "ejemplo de tarea",
					                "nombre": "Tarea de ejemplo",
					                "completada": "false",
					                "idSoporte": 1,
					                "soporte": "Juan Perez",
					                "idTicket": "1",
					                "asuntoTicket": "ejemplo de ticket",
					                
					 ]"""))),
              
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Tarea no encontrada", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                {
                    "error": "Tarea no encontrada con ID: 123"
                }""")))
            })
    public ResponseEntity<TareaDTO> getTareaById(@RequestParam("id") long id) {
        Optional<TareaDTO> tarea =  tareaService.findById(id);
        if (tarea == null) {
            throw new TareaNoEncontradaException("La tarea con ID " + id + " no existe.");
        }
        TareaDTO tareaDTO = modelMapper.map(tarea, TareaDTO.class);
        return new ResponseEntity<>(tareaDTO, HttpStatus.OK);
    }

//Crear una nueva tarea
    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crear una nueva tarea", description = "Permite crear una nueva tarea asignada a un ticket y soporte específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Tarea", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					[
					              {
					                "id": 1,
					                "descripcion": "ejemplo de tarea",
					                "nombre": "Tarea de ejemplo",
					                "completada": "false",
					                "idSoporte": 1,
					                "soporte": "Juan Perez",
					                "idTicket": "1",
					                "asuntoTicket": "ejemplo de ticket",
					                
					 ]"""))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Error inesperado")
        })
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
// DTO para la creación de una tarea
    public record TareaDTORest( long id, @NotBlank String descripcion, @NotBlank String nombre, boolean completada, long idSoporte, String soporte, long idTicket, String asuntoTicket) {}

}