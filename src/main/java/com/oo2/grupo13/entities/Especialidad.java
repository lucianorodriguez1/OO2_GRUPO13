package com.oo2.grupo13.entities;

public class Especialidad {
    private long id;
    private String nombre;

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
