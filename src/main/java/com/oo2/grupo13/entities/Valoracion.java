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

     public Valoracion( int puntaje, String comentario) {  
        this.puntaje = puntaje;
        this.comentario = comentario;
    }
}
