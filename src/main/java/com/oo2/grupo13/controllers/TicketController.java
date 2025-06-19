package com.oo2.grupo13.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.Authentication;
import com.oo2.grupo13.dtos.TicketDTOCliente;
import com.oo2.grupo13.dtos.TicketDTOSoporte;
import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.helpers.ViewRouteHelper;
import com.oo2.grupo13.services.ISoporteService;
import com.oo2.grupo13.services.ITicketService;


@Controller
@RequestMapping("/ticket")
public class TicketController {
    private ITicketService ticketService;
	private ISoporteService soporteService;
    private ModelMapper modelMapper = new ModelMapper();

    public TicketController(ITicketService ticketService, ISoporteService soporteService) {
		this.soporteService = soporteService;
        this.ticketService = ticketService;
    }
    
    @GetMapping("/verTickets")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LISTA_TICKETS);
		mAV.addObject("tickets", ticketService.getAll());
		return mAV;
	}

	@GetMapping("/verTicketsCliente")
	public ModelAndView verTicketsCliente() {
		ModelAndView mAV = new ModelAndView("ticket/ver_tickets_cliente");
		mAV.addObject("tickets", ticketService.getAll());
        mAV.addObject("ticket", new TicketDTOCliente());
		return mAV;
	}

	@GetMapping("/verTicketsSoporte")
	public ModelAndView verTicketsSoporte() {
		ModelAndView mAV = new ModelAndView("ticket/ver_tickets_soporte");
		mAV.addObject("tickets", ticketService.getAll());
        mAV.addObject("ticket", new TicketDTOSoporte());
		mAV.addObject("soportes", soporteService.getAll());
		return mAV;
	}

	@GetMapping("/modificar/{id}")
	public ModelAndView modificarTicket(@PathVariable("id") long id) {
		ModelAndView mAV = new ModelAndView("ticket/editar_ticket");
        TicketDTOSoporte ticket = ticketService.findByIdSoporte(id).get();
        mAV.addObject("ticket", ticket);
        mAV.addObject("soportes", soporteService.getAll());
        return mAV;
	}

	@PostMapping("/editar")
	public RedirectView editarTicket(@ModelAttribute("ticket") TicketDTOSoporte ticketDTO) {
		Ticket ticketEditar = ticketService.findByIdSoporte(ticketDTO.getId()).map(ticket -> modelMapper.map(ticket, Ticket.class)).get();
        ticketEditar.setEstado(ticketDTO.getEstado());
        ticketEditar.setPrioridad(ticketDTO.getPrioridad());
        
        if (ticketDTO.getSoporteAsignado() != null && ticketDTO.getSoporteAsignado().getId() != 0) {
            Soporte soporte = new Soporte();
            soporte.setId(ticketDTO.getSoporteAsignado().getId());
            ticketEditar.setSoporteAsignado(soporte);
        } else {
            ticketEditar.setSoporteAsignado(null); // Si no hay soporte, queda como null
        }
        ticketService.insertOrUpdateSoporte(modelMapper.map(ticketEditar, TicketDTOSoporte.class));
        return new RedirectView(ViewRouteHelper.VER_TICKETS_SOPORTE);
	}
	
	@GetMapping("/nuevo")
	public ModelAndView crearTicket() {
		ModelAndView mAV = new ModelAndView("ticket/generar_ticket_cliente");
		mAV.addObject("ticket", new TicketDTOCliente());
		return mAV;
	}

	@PostMapping("/crear")
    public RedirectView crearTicket(TicketDTOCliente dto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String emailCliente = ((Cliente) authentication.getPrincipal()).getEmail();
		dto.setCliente(emailCliente);
		ticketService.insertOrUpdateCliente(dto);
		return new RedirectView(ViewRouteHelper.VER_TICKETS_CLIENTE);
    }


}
