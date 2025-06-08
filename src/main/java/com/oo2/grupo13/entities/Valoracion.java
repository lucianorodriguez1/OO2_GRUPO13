package com.oo2.grupo13.entities;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Size;

import jakarta.persistence.OneToOne;

@Entity
@Getter @Setter @NoArgsConstructor
public class Valoracion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private int puntaje;

    @CreationTimestamp
    private LocalDateTime fecha; 
    
    @Size (max = 300)
    private String comentario;
    @OneToOne(mappedBy = "valoracion")
    private Ticket ticketAsociado;

    /*@OneToOne(mappedBy = "valoracion")
    private Ticket ticket; */

     public Valoracion(int id, int puntaje, LocalDateTime fecha, String comentario) {  
        this.id = id;
        this.puntaje = puntaje;
        this.fecha = fecha;
        this.comentario = comentario;
        //this.ticket = ticket;
    }
}
