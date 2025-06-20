package com.oo2.grupo13.services;
import java.util.List;
import java.util.Optional;
import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.entities.Ticket;

public interface ITareaService{
    
    public Optional<TareaDTO> findById(long id);
    public List<Tarea> getAll();
    public Tarea insertOrUpdate (TareaDTO tareaModel);
    public boolean delete (long id);
    public List<Tarea> filtrarPorEstado(boolean estado);
    public List<Tarea> filtrarPorTicket(Ticket ticket);
    public List<Tarea> filtrarPorTicketYEstado(Ticket byId, Boolean estado);
    
}
