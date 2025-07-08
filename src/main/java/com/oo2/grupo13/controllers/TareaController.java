package com.oo2.grupo13.controllers;
import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.exceptions.TareaNoEncontradaException;
import com.oo2.grupo13.helpers.ViewRouteHelper;
import com.oo2.grupo13.services.ITareaService;
import com.oo2.grupo13.services.ITicketService;
import com.oo2.grupo13.services.ISoporteService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping ("/tareas")
public class TareaController {

    private ITicketService ticketService;
    private ITareaService tareaService;
    private ISoporteService soporteService;
    private ModelMapper modelMapper = new ModelMapper();

    public TareaController(ITareaService tareaService, ISoporteService soporteService, ITicketService ticketService) {
        this.tareaService = tareaService;
        this.soporteService = soporteService;
        this.ticketService = ticketService;
    }
    
@GetMapping("/verTareasTicket/{id}")
public ModelAndView verTareasPorTicket(@PathVariable("id") long id) {
    ModelAndView mav = new ModelAndView("tareas/lista");
    mav.addObject("tareas", tareaService.filtrarPorTicket(ticketService.findById(id)));
    mav.addObject("ticketId", id); 
    return mav;
}
@GetMapping("/filtrarEstado")
public ModelAndView filtrarPorEstado( @RequestParam(name = "estado", required = false) String estado, @RequestParam("ticketId") Long ticketId) {

    ModelAndView mav = new ModelAndView("tareas/lista");
    List<Tarea> tareasFiltradas;

    Ticket ticket = ticketService.findById(ticketId);

    if (estado == null || estado.isEmpty()) {
        tareasFiltradas = tareaService.filtrarPorTicket(ticket);
    } else if ("true".equalsIgnoreCase(estado) || "false".equalsIgnoreCase(estado)) {
        Boolean estadoBool = Boolean.valueOf(estado);
        tareasFiltradas = tareaService.filtrarPorTicketYEstado(ticket, estadoBool);
    } else {
        // Por las dudas mostrar todo si el valor es inválido
        tareasFiltradas = tareaService.filtrarPorTicket(ticket);
    }

    mav.addObject("tareas", tareasFiltradas);
    mav.addObject("ticketId", ticketId);
    mav.addObject("estadoSeleccionado", estado);

    return mav;
}

@GetMapping("/nueva/{ticketId}")
public ModelAndView nuevaTarea(@PathVariable("ticketId") Long ticketId) {
    ModelAndView mav = new ModelAndView("tareas/nueva");

    TareaDTO dto = new TareaDTO();
    dto.setIdTicket(ticketId); 
    dto.setAsuntoTicket(ticketService.findById(ticketId).getAsunto());

    mav.addObject("tarea", dto);
    mav.addObject("soportes", soporteService.getAll());
    mav.addObject("ticketActualId", ticketId); 
    return mav;
}

@PostMapping("/crear")
public ModelAndView crearTarea(@ModelAttribute("tarea") TareaDTO dto) {
    tareaService.insertOrUpdate(dto);
    ModelAndView mav = new ModelAndView("tareas/nueva");

    TareaDTO nuevoDto = new TareaDTO();
    nuevoDto.setIdTicket(dto.getIdTicket());
    nuevoDto.setAsuntoTicket(dto.getAsuntoTicket());

    mav.addObject("tarea", nuevoDto);
    mav.addObject("soportes", soporteService.getAll());
    mav.addObject("ticketActualId", dto.getIdTicket());

    mav.addObject("mensaje", "Tarea creada exitosamente"); 

    return mav;
}

@GetMapping("/{id}")
public  ModelAndView getEdit(@PathVariable("id") long id) {
    ModelAndView mav = new ModelAndView(ViewRouteHelper.TAREA_EDITAR);
    TareaDTO tareaDTO = tareaService.findById(id).map(tarea -> modelMapper.map(tarea, TareaDTO.class)).orElseThrow(() -> new TareaNoEncontradaException("No se encontró la tarea con ID: " + id));
        mav.addObject("tarea", tareaDTO);
        mav.addObject("soportes", soporteService.getAll());
        mav.addObject("tickets", ticketService.getAll());
    return mav;
}

@PostMapping("/editar")
public RedirectView editarTarea(@ModelAttribute("tarea") TareaDTO tareaDTO) {
    // Primero obtengo la tarea actual para validar que exista
    TareaDTO tareaExistente = tareaService.findById(tareaDTO.getId())
        .orElseThrow(() -> new TareaNoEncontradaException("No se encontró la tarea con ID: " + tareaDTO.getId()));

    tareaDTO.setNombre(tareaExistente.getNombre());
    tareaDTO.setDescripcion(tareaExistente.getDescripcion());


    tareaService.insertOrUpdate(tareaDTO);

    return new RedirectView("/tareas/" + tareaDTO.getId());
}

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminarTarea(@PathVariable("id") int id) {

        TareaDTO tarea = tareaService.findById(id)
            .orElseThrow(() -> new TareaNoEncontradaException("No se encontró la tarea con ID: " + id));
        Long ticketId = tarea.getIdTicket(); 

        tareaService.delete(id);
        return new RedirectView("/tareas/verTareasTicket/" + ticketId);
    }
}