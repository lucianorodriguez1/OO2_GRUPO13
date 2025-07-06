package com.oo2.grupo13.services;
import com.oo2.grupo13.entities.Valoracion;
import java.util.List;

public interface IValoracionService {

    public void insertOrUpdate(Valoracion nuevaValoracion);
    public Valoracion findById(long id);
    public Valoracion findByTicketId(Long ticketId);
    public void validarValoracion(Valoracion valoracion);
}
