package com.oo2.grupo13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.dtos.TicketDTOCliente;
import com.oo2.grupo13.dtos.TicketDTOSoporte;
import com.oo2.grupo13.helpers.ViewRouteHelper;
import com.oo2.grupo13.services.ITicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    private ITicketService ticketService;

    public TicketController(ITicketService ticketService) {
        this.ticketService = ticketService;
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
		return mAV;
	}

	@GetMapping("/nuevo")
	public ModelAndView crearTicket() {
		ModelAndView mAV = new ModelAndView("ticket/generar_ticket_cliente");
		mAV.addObject("ticket", new TicketDTOCliente());
		return mAV;
	}

	@PostMapping("/crear")
    public RedirectView crearTicket(TicketDTOCliente dto) {
    ticketService.insertOrUpdateCliente(dto);
    return new RedirectView(ViewRouteHelper.VER_TICKETS_CLIENTE);
    }

}
