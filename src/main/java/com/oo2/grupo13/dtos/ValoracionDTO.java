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

    // Constructor completo
    public ValoracionDTO(int id, int puntaje, LocalDateTime fecha, String comentario) {
        this.id = id;
        this.puntaje = puntaje;
        this.fecha = fecha; 
        this.comentario = comentario;
    }
    // Constructor sin fecha ni id, se generan automáticamente
    public ValoracionDTO(int puntaje, String comentario) {
        this.puntaje = puntaje;
        this.comentario = comentario;
    }

}
