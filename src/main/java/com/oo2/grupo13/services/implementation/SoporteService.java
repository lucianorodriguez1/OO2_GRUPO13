package com.oo2.grupo13.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo13.dtos.SoporteDTO;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.TURNO;
import com.oo2.grupo13.entities.UsuarioRol;
import com.oo2.grupo13.enums.ROL;
import com.oo2.grupo13.repositories.ISoporteRepository;
import com.oo2.grupo13.repositories.IUsuarioRolRepository;
import com.oo2.grupo13.services.ISoporteService;

@Service("soporteService")
public class SoporteService implements ISoporteService{
	private ISoporteRepository soporteRepository;
	private IUsuarioRolRepository usuarioRolRepository;
	private ModelMapper modelMapper = new ModelMapper();
	public SoporteService(ISoporteRepository soporteRepository, IUsuarioRolRepository usuarioRolRepository) {
		this.soporteRepository = soporteRepository;
		this.usuarioRolRepository = usuarioRolRepository;
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
		Soporte soporte = new Soporte();
	    Optional<UsuarioRol> rolPorDefecto = usuarioRolRepository.findByRol(ROL.USUARIO);
	    
		soporte.setNombre(soporteDto.getNombre());
		soporte.setApellido(soporteDto.getApellido());
		soporte.setEmail(soporteDto.getEmail());
		soporte.setPassword(soporteDto.getPassword());
		soporte.setFotoPerfil(soporteDto.getFotoPerfil());
		soporte.setRol(rolPorDefecto.get());

		soporte.setCuil(soporteDto.getCuil());
		soporte.setFechaIngreso(soporteDto.getFechaIngreso());
		soporte.setTurno(soporteDto.getTurno());

		Soporte guardado = soporteRepository.save(soporte);

		return soporteDto;
	}

	@Override
	public boolean remove(int id) {
		return false;
	}

	@Override
	public SoporteDTO findByCuil(String cuil) {
		return null;
	}

	@Override
	public List<SoporteDTO> findByTurno(TURNO turno) {
		return null;
	}
}
