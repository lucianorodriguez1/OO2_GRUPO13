package com.oo2.grupo13.services.implementation;
import com.oo2.grupo13.entities.Valoracion;
import com.oo2.grupo13.exceptions.ValoracionInvalidaException;
import com.oo2.grupo13.repositories.IValoracionRepository;
import com.oo2.grupo13.services.IValoracionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service ("valoracionService")
public class ValoracionService implements IValoracionService{

    private IValoracionRepository valoracionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ValoracionService(IValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }
    
    public void insertOrUpdate(Valoracion nuevaValoracion) {
        Valoracion valoracion = modelMapper.map(nuevaValoracion, Valoracion.class);
        validarValoracion(valoracion);
        valoracionRepository.save(valoracion);
    }

    public void validarValoracion(Valoracion valoracion) {
        if (valoracion.getPuntaje() < 1 || valoracion.getPuntaje() > 5) {
            throw new ValoracionInvalidaException("El puntaje debe estar entre 1 y 5.");
        }
    }

    @Override
    public Valoracion findById(long id) {
        return valoracionRepository.findById(id).orElse(null);
    }

    @Override
    public Valoracion findByTicketId(Long ticketId) {
        return valoracionRepository.findByTicketId(ticketId)
                .orElse(null); // o lanzar excepción si querés manejarlo distinto
    }
    

}



