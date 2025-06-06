package com.oo2.grupo13.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Soporte extends Usuario{
   
    private List<Especialidad> especialidades;
    private String cuil;
    private LocalDate fechaIngreso;
    private TURNO turno;

    public Soporte(String nombre, String apellido, String email, String password, String fotoPerfil, String rol, String cuil, LocalDate fechaIngreso, TURNO turno) {
        super(nombre, apellido, email, password, fotoPerfil, rol);
        this.especialidades = new ArrayList<>();
        this.cuil = cuil;
        this.fechaIngreso = fechaIngreso;
        this.turno = turno;
    }

    // Getters and Setters
    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public TURNO getTurno() {
        return turno;
    }

    public void setTurno(TURNO turno) {
        this.turno = turno;
    }
}
