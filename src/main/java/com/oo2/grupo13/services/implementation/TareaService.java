package com.oo2.grupo13.services.implementation;
import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.exceptions.TareaNoEncontradaException;
import com.oo2.grupo13.repositories.ITareaRepository;
import com.oo2.grupo13.services.ITareaService;
import com.oo2.grupo13.services.ISoporteService;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service ("tareaService")
public class TareaService implements ITareaService{

    private ITareaRepository tareaRepository;           
    
    private ModelMapper modelMapper = new ModelMapper();

    public TareaService(ITareaRepository tareaRepository, ISoporteService soporteService) {
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
    public Tarea insertOrUpdate(TareaDTO tareaModel) {
        Tarea tarea;

        if (tareaModel.getId() != 0 && tareaRepository.existsById(tareaModel.getId())) {
        
            tarea = tareaRepository.findById(tareaModel.getId()).get();
        } else {
           
            tarea = new Tarea();
        }

        tarea.setNombre(tareaModel.getNombre());
        tarea.setDescripcion(tareaModel.getDescripcion());
        tarea.setCompletada(tareaModel.isCompletada());

        if (tareaModel.getSoporte() != null && tareaModel.getSoporte().getId() != 0) {
            Soporte soporte = new Soporte();
            soporte.setId(tareaModel.getSoporte().getId());
            tarea.setSoporte(soporte);
        } else {
            tarea.setSoporte(null);
        }

        tarea.setTicketAsociado(tareaModel.getTicketAsociado() != null ? modelMapper.map(tareaModel.getTicketAsociado(), Ticket.class): null);
        tarea = tareaRepository.save(tarea);
    return modelMapper.map(tarea, Tarea.class);
    }

    @Override
    public boolean delete(long id) {
        Tarea tarea = tareaRepository.findById(id).orElseThrow(() -> new TareaNoEncontradaException("No se encontró la tarea con ID: " + id));
        tareaRepository.delete(tarea);
        return true;
    }

    @Override
    public List<Tarea> filtrarPorEstado(boolean estado) {
        return tareaRepository.findByCompletada(estado);
    }

    @Override
    public List<Tarea> filtrarPorTicket(Ticket ticket) {
        return tareaRepository.findByTicketAsociado(ticket);
    }

    @Override
    public List<Tarea> filtrarPorTicketYEstado(Ticket ticket, Boolean estado) {
        return tareaRepository.findByTicketAsociadoAndCompletada(ticket, estado);
    }
}
