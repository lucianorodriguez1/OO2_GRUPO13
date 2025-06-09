package com.oo2.grupo13.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name ="descripcion", nullable = false)
    private String descripcion;
    @Column(name = "asunto", nullable = false)
    private String asunto;
    @CreationTimestamp
    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_baja", nullable = true)
    private LocalDateTime fechaBaja;
    @Column(name = "prioridad", nullable = false)
    private PRIORIDAD prioridad;
    @Column(name = "estado", nullable = false)
    private ESTADO estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @OneToMany(mappedBy = "ticketAsociado", fetch = FetchType.LAZY)
    private List<Tarea> tareas;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "valoracion_id")
    private Valoracion valoracion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soporte_id")
    private Soporte soporteAsignado;

    public Ticket(String descripcion, 
                    String asunto, 
                    LocalDateTime fechaBaja, 
                    PRIORIDAD prioridad, 
                    ESTADO estado, 
                    Cliente cliente, 
                    Soporte soporteAsignado) {
        this.descripcion = descripcion;
        this.asunto = asunto;
        this.fechaBaja = fechaBaja;
        this.prioridad = prioridad;
        this.estado = estado;
        this.cliente = cliente;
        this.tareas = new ArrayList<>();
        this.valoracion = null;
        this.soporteAsignado = soporteAsignado;
    }
  
}
