package com.oo2.grupo13.services.implementation;

import java.util.List;

import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.repositories.ITareaRepository;
import com.oo2.grupo13.services.ITareaService;

import org.modelmapper.ModelMapper;

public class TareaService implements ITareaService{

    private ITareaRepository tareaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public TareaService(ITareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public List<Tarea> getAll() {
        return tareaRepository.findAll();
    }

    @Override
    public TareaDTO insertOrUpdate(TareaDTO tareaModel) {
        Tarea tarea = tareaRepository.save(modelMapper.map(tareaModel, Tarea.class));
        return modelMapper.map(tarea, TareaDTO.class);
    }

    @Override
    public boolean delete(long id) {
        try {
            tareaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
