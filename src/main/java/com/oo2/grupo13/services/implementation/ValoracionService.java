package com.oo2.grupo13.services.implementation;

import com.oo2.grupo13.entities.Valoracion;
import com.oo2.grupo13.repositories.IValoracionRepository;
import com.oo2.grupo13.services.IValoracionService;

import java.util.List;

import org.modelmapper.ModelMapper;

public class ValoracionService implements IValoracionService{

    private IValoracionRepository valoracionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ValoracionService(IValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }
    
    
    @Override
	public List<Valoracion> getAll() {
		return valoracionRepository.findAll();
	}

	@Override
	public Valoracion insertOrUpdate(Valoracion valoracion) {
		Valoracion val = valoracionRepository.save(modelMapper.map(valoracion, Valoracion.class));
        return modelMapper.map(val, Valoracion.class);
	}

	@Override
	public boolean delete(long id) {
		try {
            valoracionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	
}
