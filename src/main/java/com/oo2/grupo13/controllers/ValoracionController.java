package com.oo2.grupo13.controllers;
import com.oo2.grupo13.dtos.ValoracionDTO;
import com.oo2.grupo13.entities.Valoracion;
import com.oo2.grupo13.helpers.ViewRouteHelper;
import com.oo2.grupo13.services.IValoracionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/valoraciones")
public class ValoracionController {

    private final IValoracionService valoracionService;

    public ValoracionController(IValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }

    @GetMapping("/lista")
     public ModelAndView listaTareas() {
        ModelAndView mav = new ModelAndView(ViewRouteHelper.VALORACION_LISTA);
        mav.addObject("valoraciones", valoracionService.getAll());
        mav.addObject("valoracion", new ValoracionDTO());
        return mav;
     }

     @GetMapping("/nueva")
    public ModelAndView nuevaValoracion() {
        ModelAndView mav = new ModelAndView(ViewRouteHelper.VALORACION_NUEVA);
        mav.addObject("valoracion", new ValoracionDTO());
        //mav.addObject("tickets", ticketService.getAll());
        return mav;
    }

    @PostMapping("/crear")
    public RedirectView crearValoracion(@ModelAttribute ("valoracion") Valoracion valoracion) {
        valoracionService.insertOrUpdate(valoracion);
        return new RedirectView(ViewRouteHelper.VALORACION_REDIRECT_LISTA);
       // ticketService.findById(ticketId).orElseThrow(() -> new TicketInexistenteException(ticketId));
    }
    
    
   @GetMapping("/editar/{id}")
    public ModelAndView editarValoracion(@PathVariable("id") Long id) {
        Valoracion valoracion = valoracionService.findById(id);
        ModelAndView mav = new ModelAndView("valoraciones/editar");
        mav.addObject("valoracion", valoracion);
        return mav;
    }

    @PostMapping("/editar")
    public RedirectView editarValoracion(@ModelAttribute("valoracion") ValoracionDTO valoracionDTO) {
        Valoracion valoracionEditar = valoracionService.findById(valoracionDTO.getId());
        valoracionEditar.setPuntaje(valoracionDTO.getPuntaje());
        valoracionEditar.setComentario(valoracionDTO.getComentario());
        valoracionService.insertOrUpdate(valoracionEditar);
        return new RedirectView("/valoraciones/lista");
    }

    @PostMapping("/eliminar/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		valoracionService.delete(id);
        return new RedirectView(ViewRouteHelper.VALORACION_REDIRECT_LISTA);
    }
}
