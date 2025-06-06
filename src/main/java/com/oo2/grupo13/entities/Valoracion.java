package com.oo2.grupo13.entities;

import java.time.LocalDateTime;

import jakarta.persistence.OneToOne;

public class Valoracion {
    private int id;
    private int puntaje;
    private LocalDateTime fecha;
    private String comentario;
    @OneToOne(mappedBy = "valoracion")
    private Ticket ticketAsociado;

    public Valoracion(int puntaje, LocalDateTime fecha, String comentario) {
        this.puntaje = puntaje;
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
