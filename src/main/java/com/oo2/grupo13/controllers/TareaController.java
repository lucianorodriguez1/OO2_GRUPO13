package com.oo2.grupo13.controllers;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.exceptions.TareaNoEncontradaException;
import com.oo2.grupo13.helpers.ViewRouteHelper;
import com.oo2.grupo13.services.ITareaService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping ("/tareas")
public class TareaController {

    private ITareaService tareaService;
    private ModelMapper modelMapper = new ModelMapper();

    public TareaController(ITareaService tareaService) {
        this.tareaService = tareaService;
    }

     // Muestra la lista de tareas
     @GetMapping("/lista")
     public ModelAndView listaTareas() {
        ModelAndView mav = new ModelAndView(ViewRouteHelper.TAREA_LISTA);
        mav.addObject("tareas", tareaService.getAll());
        mav.addObject("tarea", new TareaDTO());
        return mav;
     }
    
    @GetMapping("/nueva")
    public ModelAndView nuevaTarea() {
        ModelAndView mav = new ModelAndView(ViewRouteHelper.TAREA_NUEVA);
        mav.addObject("tarea", new TareaDTO());
        //mav.addObject("tickets", ticketService.getAll());
        return mav;
    }

    @PostMapping("/crear")
    public RedirectView crearTarea(@ModelAttribute ("tarea") Tarea tarea) {
        tareaService.insertOrUpdate(tarea);
        return new RedirectView(ViewRouteHelper.TAREA_REDIRECT_LISTA);
       // ticketService.findById(ticketId).orElseThrow(() -> new TicketInexistenteException(ticketId));
    }

    @GetMapping("/{id}")
    public  ModelAndView getEdit(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView(ViewRouteHelper.TAREA_EDITAR);
        TareaDTO tareaDTO = tareaService.findById(id)
            .map(tarea -> modelMapper.map(tarea, TareaDTO.class))
            .orElseThrow(() -> new TareaNoEncontradaException("No se encontró la tarea con ID: " + id));
        mav.addObject("tarea", tareaDTO);
        return mav;
    }

  @PostMapping("/editar")
    public RedirectView editarTarea(@ModelAttribute("tarea") TareaDTO tareaDTO) {
    Tarea tareaEditar = tareaService.findById(tareaDTO.getId()).map(tarea -> modelMapper.map(tarea, Tarea.class)).orElseThrow(() -> new TareaNoEncontradaException("No se encontró la tarea con ID: " + tareaDTO.getId()));

    tareaEditar.setNombre(tareaDTO.getNombre());
    tareaEditar.setDescripcion(tareaDTO.getDescripcion());
    tareaEditar.setCompletada(tareaDTO.isCompletada());

    tareaService.insertOrUpdate(tareaEditar);
    return new RedirectView(ViewRouteHelper.TAREA_REDIRECT_LISTA);
    }

  @PostMapping("/eliminar/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		tareaService.delete(id);
        return new RedirectView(ViewRouteHelper.TAREA_REDIRECT_LISTA);
    }


    
}
