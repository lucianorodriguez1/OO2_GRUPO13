package com.oo2.grupo13.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private UsuarioService usuarioService;
	private ModelMapper modelMapper = new ModelMapper();
	public SoporteService(ISoporteRepository soporteRepository, IUsuarioRolRepository usuarioRolRepository, UsuarioService usuarioService) {
		this.soporteRepository = soporteRepository;
		this.usuarioRolRepository = usuarioRolRepository;
		this.usuarioService = usuarioService;
	}
	
	@Override
	public List<SoporteDTO> getAll() {
	    return soporteRepository.findAll().stream()
	        .map(soporte -> modelMapper.map(soporte, SoporteDTO.class))
	        .toList();
	}

	@Override
	public Optional<SoporteDTO> findById(long id) {
		Optional<Soporte> soporteOpt = soporteRepository.findById((long) id);
		return soporteOpt.map(soporte -> modelMapper.map(soporte, SoporteDTO.class));
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
	    Soporte soporte;

	    // Buscar por ID (solo si viene con ID)
	    if (soporteDto.getId() != 0) {
	        soporte = soporteRepository.findById(soporteDto.getId())
	            .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));
	    } else {
	        soporte = new Soporte();
	        // Asignar rol por defecto solo si es nuevo
	        UsuarioRol rol = usuarioRolRepository.findByRol(ROL.SOPORTE)
	            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
	        soporte.setRol(rol);
	    }

	    soporte.setNombre(soporteDto.getNombre());
	    soporte.setApellido(soporteDto.getApellido());
	    soporte.setEmail(soporteDto.getEmail());

	    // Solo codificás la contraseña si cambió (o si es nuevo)
	    if (soporteDto.getPassword() != null && !soporteDto.getPassword().isBlank()) {
	        soporte.setPassword(new BCryptPasswordEncoder(7).encode(soporteDto.getPassword()));
	    }

	    soporte.setFotoPerfil(soporteDto.getFotoPerfil());
	    soporte.setCuil(soporteDto.getCuil());
	    soporte.setFechaIngreso(soporteDto.getFechaIngreso());
	    soporte.setTurno(soporteDto.getTurno());

	    // Validar email único (pasás el id actual)
	    usuarioService.validarEmailUnico(soporte.getEmail(), soporte.getId());

	    // Guardar
	    Soporte guardado = soporteRepository.save(soporte);

	    return modelMapper.map(guardado, SoporteDTO.class);
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
