package com.oo2.grupo13.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo13.entities.Area;

@Repository("areaRepository")
public interface IAreaRepository extends JpaRepository<Area, Integer>{
	public Optional<Area> findById(int id);
}
