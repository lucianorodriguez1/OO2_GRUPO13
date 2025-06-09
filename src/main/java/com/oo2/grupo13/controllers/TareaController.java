package com.oo2.grupo13.controllers;
import com.oo2.grupo13.services.implementation.TicketService;
import com.oo2.grupo13.dtos.SoporteDTO;
import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.exceptions.TareaNoEncontradaException;
import com.oo2.grupo13.helpers.ViewRouteHelper;
import com.oo2.grupo13.services.ITareaService;
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

    private TicketService ticketService;
    private ITareaService tareaService;
    private ISoporteService soporteService;
    private ModelMapper modelMapper = new ModelMapper();

    public TareaController(ITareaService tareaService, ISoporteService soporteService, TicketService ticketService) {
        this.tareaService = tareaService;
        this.soporteService = soporteService;
        this.ticketService = ticketService;
    }
    
// Muestro la lista de tareas
    @GetMapping("/lista")
    public ModelAndView listarTareas() {
    ModelAndView mav = new ModelAndView("tareas/lista");
    mav.addObject("tareas", tareaService.getAll());
    return mav;
    }

     // Filtrar tareas por estado (completada o no completada)
    @GetMapping("/filtrarEstado")
    public ModelAndView filtrarPorEstado(@RequestParam("estado") boolean estado) {
        ModelAndView mav = new ModelAndView("tareas/lista");
        List<Tarea> tareasFiltradas = tareaService.filtrarPorEstado(estado);
        mav.addObject("tareas", tareasFiltradas);
        return mav;
    }
    
//Creo una tarea nueva
    @GetMapping("/nueva")
    public ModelAndView nuevaTarea() {
        ModelAndView mav = new ModelAndView("tareas/nueva");
        mav.addObject("tarea", new TareaDTO());
        List<SoporteDTO> soportes = soporteService.getAll();
        mav.addObject("soportes", soportes);
        List<Ticket> tickets = ticketService.getAll();
        mav.addObject("tickets", tickets);
    return mav;
    }

    @PostMapping("/crear")
    public RedirectView crearTarea(TareaDTO dto) {
    tareaService.insertOrUpdate(dto);
    return new RedirectView(ViewRouteHelper.TAREA_REDIRECT_LISTA);
    }

//obtengo la tarea a editar por el id 
    @GetMapping("/{id}")
    public  ModelAndView getEdit(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView(ViewRouteHelper.TAREA_EDITAR);
        TareaDTO tareaDTO = tareaService.findById(id)
            .map(tarea -> modelMapper.map(tarea, TareaDTO.class))
            .orElseThrow(() -> new TareaNoEncontradaException("No se encontró la tarea con ID: " + id));
        mav.addObject("tarea", tareaDTO);
        mav.addObject("soportes", soporteService.getAll());
        mav.addObject("tickets", ticketService.getAll());
        return mav;
    }

    @PostMapping("/editar")
    public RedirectView editarTarea(@ModelAttribute("tarea") TareaDTO tareaDTO) {
    Tarea tareaEditar = tareaService.findById(tareaDTO.getId()).map(tarea -> modelMapper.map(tarea, Tarea.class)).orElseThrow(() -> new TareaNoEncontradaException("No se encontró la tarea con ID: " + tareaDTO.getId()));
    tareaEditar.setNombre(tareaDTO.getNombre());
    tareaEditar.setDescripcion(tareaDTO.getDescripcion());
    tareaEditar.setCompletada(tareaDTO.isCompletada());
    
    if (tareaDTO.getSoporte() != null && tareaDTO.getSoporte().getId() != 0) {
        Soporte soporte = new Soporte();
        soporte.setId(tareaDTO.getSoporte().getId());
        tareaEditar.setSoporte(soporte);
    } else {
        tareaEditar.setSoporte(null); // Si no hay soporte, queda como null
    }
    tareaEditar.setTicketAsociado(tareaDTO.getTicketAsociado() != null ? modelMapper.map(tareaDTO.getTicketAsociado(), Ticket.class) : null); //para probar el ticket puede ser nulo
    tareaService.insertOrUpdate(modelMapper.map(tareaEditar, TareaDTO.class));
    return new RedirectView(ViewRouteHelper.TAREA_REDIRECT_LISTA);
    }

    // Elimino la tarea por el id
    @PostMapping("/eliminar/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		tareaService.delete(id);
        return new RedirectView(ViewRouteHelper.TAREA_REDIRECT_LISTA);
    }    
    
   
}
