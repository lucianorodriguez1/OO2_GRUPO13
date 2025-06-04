package com.oo2.grupo13.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oo2.grupo13.entities.Valoracion;

public interface IValoracionRepository extends JpaRepository <Valoracion, Serializable> {

    public abstract Valoracion findById(long id);

    public abstract List<Valoracion> findByPuntajeOrderByPuntajeDesc();
    public abstract List<Valoracion> findByPuntajeOrderByPuntajeAsc();

    
    

}
