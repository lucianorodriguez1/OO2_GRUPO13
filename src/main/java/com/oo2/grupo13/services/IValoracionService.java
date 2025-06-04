package com.oo2.grupo13.services;
import com.oo2.grupo13.entities.Valoracion;

import java.util.List;


public interface IValoracionService {
    
    public List<Valoracion> getAll();
    public Valoracion insertOrUpdate(Valoracion valoracionModel);
    public boolean delete(long id);
  
}
