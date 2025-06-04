package com.oo2.grupo13.dtos;

import java.time.LocalDateTime;

import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TareaDTO {
    
    private long id; 

    private String descripcion;

    private String nombre; 

    private boolean completada;

    private LocalDateTime fechaCreacion;

    private Soporte soporte;

    private Ticket ticket;

    // Constructor vista cliente
    public TareaDTO(String descripcion, String nombre, boolean completada) {
        this.descripcion = descripcion; 
        this.nombre = nombre; 
        this.completada = completada;
    }
    // Constructor completo, vista soporte/ admin
    public TareaDTO(long id, String descripcion, String nombre, boolean completada, LocalDateTime fechaCreacion, Soporte soporte, Ticket ticket) {
        this.id = id;
        this.descripcion = descripcion; 
        this.nombre = nombre; 
        this.completada = completada;
        this.fechaCreacion = fechaCreacion;
        this.soporte = soporte;
        this.ticket = ticket;
    }
}