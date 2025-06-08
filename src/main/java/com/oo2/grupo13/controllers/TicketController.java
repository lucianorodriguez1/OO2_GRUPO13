package com.oo2.grupo13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo13.dtos.TicketDTOCliente;
import com.oo2.grupo13.services.ITicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    private ITicketService ticketService;

    public TicketController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }

    
	@GetMapping("/")
	public ModelAndView verTickets() {
		ModelAndView mAV = new ModelAndView("ticket/ver_tickets_cliente");
		mAV.addObject("tickets", ticketService.getAll());
        mAV.addObject("ticket", new TicketDTOCliente());
		return mAV;
	}
}
