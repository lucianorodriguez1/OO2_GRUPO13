package com.oo2.grupo13.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.oo2.grupo13.dtos.SoporteDTO;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.TURNO;

public interface ISoporteService {
	public List<SoporteDTO> getAll(); 
    public Optional<SoporteDTO> findById(long id); 
    public Optional<SoporteDTO> findByCuil(String cuil);
    public List<SoporteDTO> findByTurno(TURNO turno);
    public List<SoporteDTO> findByEspecialidad(String nombreEspecialidad);
    public List<SoporteDTO> findByFechaIngreso(LocalDate fechaIngreso);
    List<SoporteDTO> findByFechaIngresoBetween(LocalDate desde, LocalDate hasta);
    public SoporteDTO insertOrUpdate(SoporteDTO soporteDto); 
    public boolean remove(int id);
}
