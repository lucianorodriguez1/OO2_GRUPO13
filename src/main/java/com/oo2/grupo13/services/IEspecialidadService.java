package com.oo2.grupo13.services;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo13.dtos.EspecialidadDTO;
import com.oo2.grupo13.entities.Especialidad;

public interface IEspecialidadService {
	public List<EspecialidadDTO> getAll();
	public Optional<EspecialidadDTO> findById(int id) throws Exception;
	public EspecialidadDTO findByName(String name);
	public EspecialidadDTO insertOrUpdate(EspecialidadDTO especialidadModel);
	public boolean remove(int id);


}
