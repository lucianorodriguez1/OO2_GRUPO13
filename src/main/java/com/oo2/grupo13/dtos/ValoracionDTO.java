package com.oo2.grupo13.dtos;

import java.time.LocalDateTime;

import com.oo2.grupo13.entities.Ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ValoracionDTO {
    private int id;
    private int puntaje; 
    private LocalDateTime fecha; 
    private String comentario; 
    private Ticket ticket; 

    public ValoracionDTO(int id, int puntaje, LocalDateTime fecha, String comentario, Ticket ticket) {
        this.setId(id);
        this.puntaje = puntaje;
        this.setFecha(fecha);
        this.comentario = comentario;
        this.ticket = ticket;
    }
}
