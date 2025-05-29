package com.oo2.grupo13.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
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

    public Ticket(String descripcion, String asunto, LocalDateTime fechaAlta, LocalDateTime fechaBaja, PRIORIDAD prioridad, ESTADO estado, Cliente cliente) {
        this.descripcion = descripcion;
        this.asunto = asunto;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.prioridad = prioridad;
        this.estado = estado;
        this.cliente = cliente;
        this.tareas = new ArrayList<>();
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDateTime fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public PRIORIDAD getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PRIORIDAD prioridad) {
        this.prioridad = prioridad;
    }

    public ESTADO getEstado() {
        return estado;
    }

    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public Valoracion getValoracion() {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion) {
        this.valoracion = valoracion;
    }

    public Soporte getSoporteAsignado() {
        return soporteAsignado;
    }

    public void setSoporteAsignado(Soporte soporteAsignado) {
        this.soporteAsignado = soporteAsignado;
    }
}
