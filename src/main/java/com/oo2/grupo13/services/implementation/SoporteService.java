package com.oo2.grupo13.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo13.dtos.SoporteDTO;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.TURNO;
import com.oo2.grupo13.repositories.ISoporteRepository;
import com.oo2.grupo13.services.ISoporteService;

@Service("soporteService")
public class SoporteService implements ISoporteService{
	private ISoporteRepository soporteRepository;
	private ModelMapper modelMapper = new ModelMapper();
	public SoporteService(ISoporteRepository soporteRepository) {
		this.soporteRepository = soporteRepository;
	}
	
	@Override
	public List<SoporteDTO> getAll() {
	    return soporteRepository.findAll().stream()
	        .map(soporte -> modelMapper.map(soporte, SoporteDTO.class))
	        .toList();
	}
	
	@Override
	public Optional<SoporteDTO> findById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public List<SoporteDTO> findByEspecialidad(String nombreEspecialidad) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SoporteDTO> findByFechaIngreso(LocalDate fechaIngreso) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SoporteDTO> findByFechaIngresoBetween(LocalDate desde, LocalDate hasta) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SoporteDTO insertOrUpdate(SoporteDTO soporteDto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean remove(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SoporteDTO findByCuil(String cuil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SoporteDTO> findByTurno(TURNO turno) {
		// TODO Auto-generated method stub
		return null;
	}
}
