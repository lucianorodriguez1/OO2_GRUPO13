package com.oo2.grupo13.services;
import com.oo2.grupo13.entities.Valoracion;
import java.util.List;

public interface IValoracionService {

    public List<Valoracion> getAll();
    public void insertOrUpdate(Valoracion nuevaValoracion);
    public boolean delete(long id);
    public Valoracion findById(long id);
    public List<Valoracion> getByPuntajeDesc();
    public List<Valoracion> getByPuntajeAsc();
    public List<Valoracion> getByPuntaje(int puntaje);
    public void validarValoracion(Valoracion valoracion);
}
