package com.oo2.grupo13.entities;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descripcion;

    private String nombre;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaActualizacion; 

    private boolean completada;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soporte_id", nullable = true)
    private Soporte soporte;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = true)
    private Ticket ticketAsociado;

    public Tarea(String descripcion, String nombre, boolean completada, Soporte soporte, Ticket ticket) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.completada = completada;
        this.soporte = soporte;
        this.ticketAsociado = ticket;
    }
    
}
