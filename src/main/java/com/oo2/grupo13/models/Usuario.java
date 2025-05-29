package com.oo2.grupo13.models;

public abstract class Usuario {
    protected long id;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String password;
    protected String fotoPerfil;
    protected String rol;

    public Usuario(String nombre, String apellido, String email, String password, String fotoPerfil, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.fotoPerfil = fotoPerfil;
        this.rol = rol;
    }
    
    
}
