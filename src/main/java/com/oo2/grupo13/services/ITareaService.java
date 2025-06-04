package com.oo2.grupo13.services;

import java.util.List;

import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Tarea;

public interface ITareaService{
    
    public List<Tarea> getAll();

    public TareaDTO insertOrUpdate (TareaDTO tareaModel);

    public boolean delete (long id);
    
}
