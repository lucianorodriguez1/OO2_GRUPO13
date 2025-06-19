package com.oo2.grupo13.dtos;

import java.time.LocalDateTime;

import com.oo2.grupo13.entities.ESTADO;

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
    private ESTADO estado;
    private String cliente;
    private String soporteAsignado;
}