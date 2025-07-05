package com.oo2.grupo13.controllers.api.v1;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo13.dtos.SoporteDTO;
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
	
}
