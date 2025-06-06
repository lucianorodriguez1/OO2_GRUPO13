package com.oo2.grupo13.services;
import java.util.List;
import java.util.Optional;
import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Tarea;

public interface ITareaService{
    public Optional<TareaDTO> findById(long id);
    public List<Tarea> getAll();
    public Tarea insertOrUpdate (Tarea tareaEditar);
    public boolean delete (long id);
    public List<TareaDTO> filtrarPorEstado(boolean estado);
   //public List<TareaDTO> filtrarPorUsuario(Long usuarioId);
}
