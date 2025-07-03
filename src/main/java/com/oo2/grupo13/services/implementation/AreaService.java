package com.oo2.grupo13.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo13.entities.Area;
import com.oo2.grupo13.repositories.IAreaRepository;

@Service
public class AreaService {
	
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

}
