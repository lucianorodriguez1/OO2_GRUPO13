package com.oo2.grupo13.entities;

public class Cliente extends Usuario {
    private Area area;

    public Cliente(String nombre, String apellido, String email, String password, String fotoPerfil, String rol, Area area) {
        super(nombre, apellido, email, password, fotoPerfil, rol);
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
