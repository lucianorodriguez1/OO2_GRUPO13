package com.oo2.grupo13.models;

import java.time.LocalDateTime;

public class Tarea {
    private long id;
    private String descripcion;
    private String nombre;
    private LocalDateTime fechaCreacion;
    private boolean completada;
    private Soporte soporte;

    public Tarea(String descripcion, String nombre, LocalDateTime fechaCreacion, boolean completada, Soporte soporte) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.completada = completada;
        this.soporte = soporte;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public Soporte getSoporte() {
        return soporte;
    }

    public void setSoporte(Soporte soporte) {
        this.soporte = soporte;
    }
}
