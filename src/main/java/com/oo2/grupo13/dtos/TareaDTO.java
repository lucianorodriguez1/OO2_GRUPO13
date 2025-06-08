package com.oo2.grupo13.dtos;
import com.oo2.grupo13.entities.Soporte;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
public class TareaDTO {
    private long id; 
    private String descripcion;
    private String nombre; 
    private boolean completada;
    private Soporte soporte;
    private Long ticketId;

    public TareaDTO(long id, String descripcion, String nombre, boolean completada, Soporte soporte, long ticketId) {
        this.id = id;
        this.descripcion = descripcion; 
        this.nombre = nombre; 
        this.completada = completada;
        this.soporte = soporte;
        this.ticketId = ticketId;
    }
}