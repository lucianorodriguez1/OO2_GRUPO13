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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

@Entity
@Getter @Setter @NoArgsConstructor
public class Valoracion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 1, message = "El puntaje debe ser al menos 1")
    @Max(value = 10, message = "El puntaje no puede ser mayor a 10")
    private int puntaje;

    @CreationTimestamp
    private LocalDateTime fecha; 

    @Size (max = 300)
    private String comentario;

    // Constructor completo
     public Valoracion(int id, int puntaje, LocalDateTime fecha, String comentario) {
        this.id = id;
        this.puntaje = puntaje;
        this.fecha = fecha;
        this.comentario = comentario;
    }
}
