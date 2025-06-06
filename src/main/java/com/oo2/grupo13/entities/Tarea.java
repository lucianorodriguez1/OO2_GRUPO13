package com.oo2.grupo13.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor
public class Tarea {
    private long id;
    private String descripcion;
    private String nombre;
    private LocalDateTime fechaCreacion;
    private boolean completada;
    private Soporte soporte;
    private Ticket ticketAsociado;

    public Tarea(String descripcion, String nombre, LocalDateTime fechaCreacion, boolean completada, Soporte soporte, Ticket ticketAsociado) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.completada = completada;
        this.soporte = soporte;
        this.ticketAsociado = ticketAsociado;
    }
    
}
