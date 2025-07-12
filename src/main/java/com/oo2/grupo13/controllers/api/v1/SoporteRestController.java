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
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
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
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping("/api/v1/soporte")

public class SoporteRestController {
	@Autowired
	@Qualifier("soporteService")
	private ISoporteService soporteService;

	private ModelMapper modelMapper = new ModelMapper();

	// GET /api/v1/soporte
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Obtener todos los soportes", description = "Permite obtener una lista de todos los soportes registrados en el sistema. Debe ser administrador para poder accederla.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Lista de soportes", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					[
					              {
					                "id": 1,
					                "nombre": "Juan",
					                "apellido": "Pérez",
					                "email": "juan@empresa.com",
					                "fotoPerfil": null,
					                "cuil": "20-12345678-9",
					                "fechaIngreso": "2023-01-01",
					                "turno": "TARDE",
					                "rol": {
										"id": 3,
										"rol": "SOPORTE",
										"fechaCreacion": "2025-06-20T13:10:59.478123",
										"fechaActualizacion": "2025-06-20T13:10:59.478123"
								},
					                "especialidades": ["Redes", "Bases de datos"]
					        	}
					 ]"""))),
			@ApiResponse(responseCode = "500", description = "ERROR - Internal server error", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{"error": "Ocurrió un error inesperado al obtener los soportes"}"""))) })
	public ResponseEntity<List<SoporteDTO>> index() {
		List<SoporteDTO> soportesDTO = new ArrayList<SoporteDTO>();
		soportesDTO = soporteService.getAll();

		return new ResponseEntity<List<SoporteDTO>>(soportesDTO, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Crear nuevo soporte", description = "Permite registrar un nuevo soporte en el sistema. Debe ser administrador para poder usar esta función.")
	@ApiResponses(value = {
			@ApiResponse(
				    responseCode = "201",
				    description = "CREATED - Soporte creado correctamente",
				    content = @Content(
				        mediaType = "application/json",
				        examples = @ExampleObject(value = """
				        {
				          "id": 8,
				          "nombre": "string",
				          "apellido": "string",
				          "email": "stringDeUsuario",
				          "password": "$2a$07$c9aEzSUgI5iB.Pq6JKvZl.xvX85WRQgxguCsbDM5OWYs33rALUBnK",
				          "fotoPerfil": "string",
				          "rol": {
				            "id": 3,
				            "rol": "SOPORTE",
				            "fechaCreacion": "2025-06-20T13:10:59.478123",
				            "fechaActualizacion": "2025-06-20T13:10:59.478123"
				          },
				          "especialidades": null,
				          "cuil": "stringprueba",
				          "fechaIngreso": "2025-07-11",
				          "turno": "MANANA"
				        }
				        """)
				    )
				),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST - CUIL duplicado o datos inválidos", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					    {
					      "error": "documento con cuil duplicado"
					    }
					"""))),
			@ApiResponse(responseCode = "500", description = "ERROR - Internal server error", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{"error": "Ocurrió un error inesperado al crear un soporte"}"""))) })
	public ResponseEntity create(@RequestBody SoporteRestDTO restDTO) {
		if (restDTO == null)
			return new ResponseEntity<String>("soporte no existe", HttpStatus.BAD_REQUEST);
		Optional<SoporteDTO> existente = soporteService.findByCuil(restDTO.cuil());
		if (existente.isPresent())
			return new ResponseEntity<String>("documento con cuil duplicado", HttpStatus.BAD_REQUEST);
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

	public record SoporteRestDTO(String nombre, String apellido, String email, String password, String fotoPerfil,
			String cuil, LocalDate fechaIngreso, TURNO turno) {
	}

}
