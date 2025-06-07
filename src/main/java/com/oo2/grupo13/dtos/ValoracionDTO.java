package com.oo2.grupo13.dtos;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ValoracionDTO {
    private int id;
    private int puntaje; 
    private LocalDateTime fecha; 
    private String comentario; 
    private Long ticketId; 

    public ValoracionDTO(int id, int puntaje, LocalDateTime fecha, String comentario, Long ticketId) {
        this.setId(id);
        this.puntaje = puntaje;
        this.setFecha(fecha);
        this.comentario = comentario;
        this.ticketId = ticketId;
    }
}
