package com.oo2.grupo13.dtos;

import java.time.LocalDateTime;
import java.util.List;


import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.ESTADO;
import com.oo2.grupo13.entities.PRIORIDAD;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.entities.Valoracion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TicketDTOSoporte {
    private long id;
    private String descripcion;
    private String asunto;
    private LocalDateTime fechaAlta;
    private LocalDateTime fechaBaja;
    private PRIORIDAD prioridad;
    private ESTADO estado;
    private Cliente cliente;
    private List<Tarea> tareas;
    private Valoracion valoracion;
    private Soporte soporteAsignado;

    public TicketDTOSoporte(long id, String descripcion, String asunto, LocalDateTime fechaAlta, LocalDateTime fechaBaja,
                            PRIORIDAD prioridad, ESTADO estado, Cliente cliente, List<Tarea> tareas,
                            Valoracion valoracion, Soporte soporteAsignado) {
        this.id = id;
        this.descripcion = descripcion;
        this.asunto = asunto;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.prioridad = prioridad;
        this.estado = estado;
        this.cliente = cliente;
        this.tareas = tareas;
        this.valoracion = valoracion;
        this.soporteAsignado = soporteAsignado;
    }
}
