package com.oo2.grupo13.controllers.api.v1;

import java.time.LocalDate;
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
import com.oo2.grupo13.entities.TURNO;
import com.oo2.grupo13.services.ISoporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema; 
@RestController
@RequestMapping("/api/v1/soporte")

public class SoporteRestController {
	@Autowired
	@Qualifier("soporteService")
	private ISoporteService soporteService;
	
	private ModelMapper modelMapper = new ModelMapper();

	//GET /api/v1/soporte 
	@GetMapping("/")
	@Operation(
			summary = "Obtener todos los soportes",
			description = "Permite obtener una lista de todos los soportes registrados en el sistema"
	)
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "OK - Soportes cargados correctamente"),
	         @ApiResponse(responseCode = "500", description = "ERROR - Internal server error",  content = @Content(
	                 mediaType = "application/json",
	                 schema = @Schema(implementation = String.class)
	             ))
	})
	public ResponseEntity<List<SoporteDTO>> index(){
		List<SoporteDTO> soportesDTO = new ArrayList<SoporteDTO>();
		soportesDTO = soporteService.getAll();
		
		return new ResponseEntity<List<SoporteDTO>>(soportesDTO,HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/")
	public ResponseEntity create(@RequestBody SoporteRestDTO restDTO) {
		if(restDTO == null) return new ResponseEntity<String>("soporte no existe", HttpStatus.BAD_REQUEST);
		Optional<SoporteDTO> existente = soporteService.findByCuil(restDTO.cuil());
		 if(existente.isPresent()) return new ResponseEntity<String>("documento con cuil duplicado", HttpStatus.BAD_REQUEST);
		try {
	        SoporteDTO soporteDTO = new SoporteDTO();
	        soporteDTO.setNombre(restDTO.nombre());
	        soporteDTO.setApellido(restDTO.apellido());
	        soporteDTO.setEmail(restDTO.email());
	        soporteDTO.setPassword(restDTO.password());
	        soporteDTO.setFotoPerfil(restDTO.fotoPerfil());
	        soporteDTO.setCuil(restDTO.cuil());
	        soporteDTO.setFechaIngreso(restDTO.fechaIngreso());
	        soporteDTO.setTurno(restDTO.turno());
	        SoporteDTO guardado = soporteService.insertOrUpdate(modelMapper.map(soporteDTO, SoporteDTO.class));
	        return new ResponseEntity(modelMapper.map(guardado, SoporteDTO.class), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public record SoporteRestDTO(String nombre,
		    String apellido,
		    String email,
		    String password,
		    String fotoPerfil,
		    String cuil,
		    LocalDate fechaIngreso,
		    TURNO turno) {}

	
}
