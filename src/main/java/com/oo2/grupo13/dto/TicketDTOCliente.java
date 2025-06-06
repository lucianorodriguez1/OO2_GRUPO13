package com.oo2.grupo13.dto;

import java.time.LocalDateTime;

import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.Soporte;

public class TicketDTOCliente {
    private long id;
    private String descripcion;
    private String asunto;
    private LocalDateTime fechaAlta;
    private LocalDateTime fechaBaja;
    private Cliente cliente;
    private Soporte soporteAsignado;
}
