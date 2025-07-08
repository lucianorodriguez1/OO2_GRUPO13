package com.oo2.grupo13.services.implementation;
import com.oo2.grupo13.dtos.TareaDTO;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Tarea;
import com.oo2.grupo13.entities.Ticket;
import com.oo2.grupo13.exceptions.TareaNoEncontradaException;
import com.oo2.grupo13.repositories.ITareaRepository;
import com.oo2.grupo13.services.ITareaService;
import com.oo2.grupo13.services.ISoporteService;
import com.oo2.grupo13.services.ITicketService;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service ("tareaService")
public class TareaService implements ITareaService{
    private ITareaRepository tareaRepository;           
    private ISoporteService soporteService;
    private ITicketService ticketService;
    
    private ModelMapper modelMapper = new ModelMapper();

    public TareaService(ITareaRepository tareaRepository, ISoporteService soporteService, ITicketService ticketService) {
        this.tareaRepository = tareaRepository;
        this.soporteService = soporteService;
        this.ticketService = ticketService;
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

    if (tareaModel.getIdSoporte() != null && tareaModel.getIdSoporte() != 0) {
    Soporte soporte = modelMapper.map(
        soporteService.findById(tareaModel.getIdSoporte())
            .orElseThrow(() -> new RuntimeException("Soporte no encontrado")),
        Soporte.class
    );
    tarea.setSoporte(soporte);
} else {
    tarea.setSoporte(null);
}


    // Ticket
    if (tareaModel.getIdTicket() != 0) {
        Ticket ticket = ticketService.findById(tareaModel.getIdTicket());
        if (ticket == null) {
            throw new RuntimeException("Ticket no encontrado");
        }
        tarea.setTicketAsociado(ticket);
    } else {
        tarea.setTicketAsociado(null);
    }

    tarea = tareaRepository.save(tarea);
    return tarea;
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
