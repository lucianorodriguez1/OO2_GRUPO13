package com.oo2.grupo13.dtos;

import java.time.LocalDateTime;

import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.Soporte;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TicketDTOCliente {
    private long id;
    private String descripcion;
    private String asunto;
    private LocalDateTime fechaAlta;
    private LocalDateTime fechaBaja;
    private String cliente;
    private String soporteAsignado;
}