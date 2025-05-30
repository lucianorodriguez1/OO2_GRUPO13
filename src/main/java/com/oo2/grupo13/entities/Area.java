package com.oo2.grupo13.entities;

import lombok.Getter;

@Getter
public class Area {
    private int id;
    private String nombre;

    public Area(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
}
