package com.oo2.grupo13.models;

public enum PRIORIDAD {
    ALTA("Alta"),
    MEDIA("Media"),
    BAJA("Baja"),
    URGENTE("Urgente");

    private final String descripcion;

    PRIORIDAD(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
