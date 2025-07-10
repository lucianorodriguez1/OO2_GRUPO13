package com.oo2.grupo13.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oo2.grupo13.entities.Area;
import com.oo2.grupo13.repositories.IAreaRepository;
import com.oo2.grupo13.services.IAreaService;

@Service("areaService")
public class AreaService implements IAreaService {
	
	private IAreaRepository areaRepository;
	
	public AreaService(IAreaRepository areaRepository) {
		this.areaRepository = areaRepository;
	}

	public List<Area> findByIds(List<Integer> ids) {
		return areaRepository.findAllById(ids);
	}
	
	public List<Area> findAll() {
		return areaRepository.findAll();
	}
	
	public Optional<Area> findById(int id) {
		return areaRepository.findById(id);
	}

}
