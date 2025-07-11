package com.oo2.grupo13.controllers.api.v1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo13.dtos.ClienteDTO;
import com.oo2.grupo13.entities.Area;
import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.services.IAreaService;
import com.oo2.grupo13.services.IClienteService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.web.bind.annotation.RequestBody;

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
	@Operation(summary = "Obtener lista de clientes", description = "Permite obtener la lista de usuarios con rol cliente registrados en el sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Lista de clientes", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					[
					   		{
							    "id": 21,
							    "nombre": "camia",
							    "apellido": "martello",
							    "email": "camila@gmail.com.ar",
							    "password": "$2a$07$uZ3uYiSqiQHvoJzAAN4li.yDRP1Pk3ZslmxIoB.4ktYf/zjulCho.",
							    "fotoPerfil": null,
							    "rol": {
							      "id": 2,
							      "rol": "CLIENTE",
							      "fechaCreacion": "2025-06-19T11:08:49.90005",
							      "fechaActualizacion": "2025-06-19T11:08:49.90005"
							    },
							    "areaIds": [
							      1
							    ]
							  }
					                
					 ]"""))),

            @ApiResponse(responseCode = "500", description = "ERROR - Internal server error", 
                content = @Content(mediaType = "application/json", 
                    examples = @ExampleObject(value = """
                        {
                            "error": "Ocurrió un error inesperado al obtener los clientes"
                        }"""))
            )})
	@GetMapping("")
	public ResponseEntity<List<ClienteDTO>> index() {
		List<ClienteDTO> clientes = clienteService.getAll();
		
		return new ResponseEntity<List<ClienteDTO>>(clientes, HttpStatus.OK);
	}
	
	// POST /api/v1/cliente
	@Operation(
			summary = "Crear un nuevo cliente",
	        description = "Permite dar de alta a un cliente cuyo email no se encuentre registrado en el sistema",
	        responses = {
	            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
	            @ApiResponse(responseCode = "400", description = "Error en la solicitud (por ejemplo, área no encontrada o email duplicado)"),
	            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
	        }
	)
	@PostMapping("")
	public ResponseEntity<String> crearCliente(@RequestBody ClienteCreateDTO clienteDTO) {
	   try {
	        Cliente cliente = new Cliente();
	        cliente.setNombre(clienteDTO.nombre());
	        cliente.setApellido(clienteDTO.apellido());
	        cliente.setEmail(clienteDTO.email());
	        cliente.setPassword(new BCryptPasswordEncoder(7).encode(clienteDTO.password())); 

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
	    }
	}

	
	public record ClienteCreateDTO(
		    String nombre,
		    String apellido,
		    String email,
		    String password,
		    List<Integer> areaIds
	) {}

}

