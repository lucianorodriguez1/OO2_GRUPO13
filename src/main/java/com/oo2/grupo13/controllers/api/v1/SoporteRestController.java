package com.oo2.grupo13.controllers.api.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo13.dtos.SoporteDTO;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.repositories.ISoporteRepository;
import com.oo2.grupo13.services.ISoporteService;

@RestController
@RequestMapping("/api/v1/soporte")

public class SoporteRestController {
	@Autowired
	@Qualifier("soporteService")
	private ISoporteService soporteService;
	
	private ModelMapper modelMapper = new ModelMapper();

	//GET /api/v1/soporte 
	@GetMapping("/")
	public ResponseEntity<List<SoporteDTO>> index(){
		List<SoporteDTO> soportesDTO = new ArrayList<SoporteDTO>();
		soportesDTO = soporteService.getAll();
		
		return new ResponseEntity<List<SoporteDTO>>(soportesDTO,HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/")
	public ResponseEntity create(@RequestBody SoporteDTO soporte) {
		if(soporte == null) return new ResponseEntity<String>("soporte does not exists", HttpStatus.BAD_REQUEST);
		Optional<SoporteDTO> existente = soporteService.findByCuil(soporte.getCuil());
		 if(existente.isPresent()) return new ResponseEntity<String>("duplicated document", HttpStatus.BAD_REQUEST);
		try {
			soporteService.insertOrUpdate(modelMapper.map(soporte, SoporteDTO.class));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(modelMapper.map(soporte, SoporteDTO.class), HttpStatus.CREATED);
	}
	
	
	
	
}
