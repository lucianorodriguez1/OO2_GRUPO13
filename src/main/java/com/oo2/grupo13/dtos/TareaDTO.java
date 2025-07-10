package com.oo2.grupo13.dtos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
public class TareaDTO {
    private long id; 
    private String descripcion;
    private String nombre; 
    private boolean completada;
    private Long idSoporte;
    private String soporte;
    private Long idTicket;
    private String asuntoTicket; 

    public TareaDTO(long id, String descripcion, String nombre, boolean completada, Long idSoporte, String soporte, Long idTicket, String asuntoTicket) {
        this.id = id;
        this.descripcion = descripcion; 
        this.nombre = nombre; 
        this.completada = completada;
        this.idSoporte = idSoporte;
        this.soporte = soporte;
        this.idTicket = idTicket;
        this.asuntoTicket = asuntoTicket;
    }
}