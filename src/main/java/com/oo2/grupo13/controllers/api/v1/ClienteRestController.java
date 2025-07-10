package com.oo2.grupo13.controllers.api.v1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo13.dtos.ClienteDTO;
import com.oo2.grupo13.entities.Area;
import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.services.IAreaService;
import com.oo2.grupo13.services.IClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteRestController {
	// inyeccion de dependencias
	@Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;
	
	@Autowired
    private IAreaService areaService;
	
	// GET /api/v1/cliente
	@Operation(summary = "Obtener todos los clientes")
	@GetMapping("")
	public ResponseEntity<List<ClienteDTO>> index() {
		List<ClienteDTO> clientes = clienteService.getAll();
		
		return new ResponseEntity<List<ClienteDTO>>(clientes, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteCreateDTO> crearCliente(@RequestBody ClienteCreateDTO clienteDTO) {
		System.out.println("JSON recibido:");
	    System.out.println("Nombre: " + clienteDTO.nombre());
	    System.out.println("Apellido: " + clienteDTO.apellido());
	    System.out.println("Email: " + clienteDTO.email());
	    System.out.println("Password: " + clienteDTO.password());
	    System.out.println("Area IDs: " + clienteDTO.areaIds()); ;
	   /* try {
	        Cliente cliente = new Cliente();
	        cliente.setNombre(clienteDTO.nombre());
	        cliente.setApellido(clienteDTO.apellido());
	        cliente.setEmail(clienteDTO.email());
	        cliente.setPassword(clienteDTO.password()); 

	        Set<Area> areas = new HashSet<>();
	        for (Integer areaId : clienteDTO.areaIds()) {
	            Area area = areaService.findById(areaId)
	                    .orElseThrow(() -> new RuntimeException("Área con ID " + areaId + " no existe"));
	            areas.add(area);
	        }
	        cliente.setAreas(areas);

	        clienteService.crearOActualizarCliente(cliente);

	        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado exitosamente");

	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Error al crear cliente: " + e.getMessage());
	    }*/
	    return ResponseEntity.ok(clienteDTO);
	}

	
	public record ClienteCreateDTO(
		    String nombre,
		    String apellido,
		    String email,
		    String password,
		    List<Integer> areaIds
	) {}

}

