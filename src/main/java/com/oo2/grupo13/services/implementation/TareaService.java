package com.oo2.grupo13.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.exceptions.TareaNoEncontradaException;
import com.oo2.grupo13.repositories.ITareaRepository;
import com.oo2.grupo13.services.ITareaService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service ("tareaService")
public class TareaService implements ITareaService{

    private ITareaRepository tareaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public TareaService(ITareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public Optional<TareaDTO> findById(long id){
        Tarea tarea = tareaRepository.findById(id).orElseThrow(() -> new TareaNoEncontradaException("No se encontró la tarea con ID: " + id));
        return Optional.of(modelMapper.map(tarea, TareaDTO.class));
    }

@Override
    public List<Tarea> getAll() {
        return tareaRepository.findAll();
    }


    @Override
    public Tarea insertOrUpdate(Tarea tareaModel) {
        Tarea savedTarea = tareaRepository.save(tareaModel);
        return modelMapper.map(savedTarea, Tarea.class);
    }

    @Override
    public boolean delete(long id) {
        Tarea tarea = tareaRepository.findById(id).orElseThrow(() -> new TareaNoEncontradaException("No se encontró la tarea con ID: " + id));
        tareaRepository.delete(tarea);
        return true;
    }

    @Override
    public List<TareaDTO> filtrarPorEstado(boolean estado) {
        List<Tarea> tareas = tareaRepository.findByCompletada(estado);
        return tareas.stream().map(tarea -> modelMapper.map(tarea, TareaDTO.class)).toList();
    }

    /* 
    @Override
    public List<TareaDTO> filtrarPorUsuario(Long usuarioId) {
       List<Tarea> tareas = tareaRepository.findBySoporteId(usuarioId);
        return tareas.stream().map(tarea -> modelMapper.map(tarea, TareaDTO.class)).toList();
    } *///falta el soporte

}
