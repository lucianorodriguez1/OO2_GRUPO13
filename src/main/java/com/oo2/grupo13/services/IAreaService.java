package com.oo2.grupo13.services;

import java.util.List;

import com.oo2.grupo13.entities.Area;

public interface IAreaService {
	List<Area> findAll();
	public List<Area> findByIds(List<Integer> ids);
}
