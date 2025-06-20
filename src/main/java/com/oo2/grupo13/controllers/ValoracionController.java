package com.oo2.grupo13.controllers;
import com.oo2.grupo13.dtos.ValoracionDTO;
import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.entities.Valoracion;
import com.oo2.grupo13.services.IValoracionService;
import com.oo2.grupo13.services.ITicketService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
@Controller
@RequestMapping("/valoraciones")
public class ValoracionController {

    private final IValoracionService valoracionService;
    private final ITicketService ticketService;

    public ValoracionController(IValoracionService valoracionService, ITicketService ticketService) {
        this.valoracionService = valoracionService;
        this.ticketService = ticketService;
    }

@GetMapping("/nueva/{ticketId}")
public ModelAndView nuevaValoracion(@PathVariable Long ticketId) {
    ModelAndView mav = new ModelAndView();

    Ticket ticket = ticketService.findById(ticketId);
    if (ticket.getValoracion() != null) {
        mav.setViewName("redirect:/ticket/verTicketsCliente?error=Este ticket ya fue valorado.");
        return mav;
    }

    ValoracionDTO valoracionDTO = new ValoracionDTO();
    valoracionDTO.setTicket(ticket);

    mav.setViewName("valoraciones/nueva");
    mav.addObject("valoracion", valoracionDTO);
    return mav;
}
@PostMapping("/crear/{ticketId}")
public RedirectView crearValoracion(@PathVariable Long ticketId, @ModelAttribute("valoracion") ValoracionDTO valoracionDTO) {
    Ticket ticket = ticketService.findById(ticketId);

    // Evitar crear si ya está valorado
    if (ticket.getValoracion() != null) {
        return new RedirectView("/ticket/verTicketsCliente?error=Este ticket ya fue valorado.");
    }

    Valoracion valoracion = new Valoracion();
    valoracion.setPuntaje(valoracionDTO.getPuntaje());
    valoracion.setComentario(valoracionDTO.getComentario());
    valoracion.setFecha(LocalDateTime.now()); // Si tu entidad lo tiene
    valoracion.setTicket(ticket);

    valoracionService.insertOrUpdate(valoracion);

    return new RedirectView("/ticket/verTicketsCliente?success=Valoración registrada correctamente.");
}
    @GetMapping("/ver/{ticketId}")
    public ModelAndView verValoracionPorTicket(@PathVariable Long ticketId) {
        ModelAndView mav = new ModelAndView("valoraciones/ver");

        Valoracion valoracion = valoracionService.findByTicketId(ticketId);
        if (valoracion == null) {
            mav.setViewName("redirect:/ticket/verTicketsCliente");
            mav.addObject("mensaje", "Aún no hay valoración para este ticket.");
            return mav;
        }
        mav.addObject("valoracion", valoracion);
        return mav;
    }
}
