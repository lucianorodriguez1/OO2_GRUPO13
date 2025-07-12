package com.oo2.grupo13.controllers.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo13.services.IUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/login")
public class LoginRestController {

    private IUsuarioService usuarioService;
    private ModelMapper modelMapper = new ModelMapper();

    public LoginRestController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @Operation(summary = "Iniciar sesión", description = "Permite iniciar sesión. Debe enviar un JSON con el email y la contraseña del usuario. No tiene ninguna función real, ya que el verdadero log in corresponde al botón \"Authorize\"")
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario autenticado correctamente", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{
					    Usuario autenticado correctamente  
                    }"""))),
			@ApiResponse(responseCode = "403", description = "ERROR - La contraseña es incorrecta", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{"error": "La contraseña es incorrecta"}"""))),
                    @ApiResponse(responseCode = "404", description = "ERROR - El usuario no existe", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{"error": "El usuario no existe"}"""))) })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody UsuarioRecord usuario) {
        try {
            return new ResponseEntity<String>(usuarioService.authenticate(usuario.email(), usuario.password()), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<String>("\"error\": \"La contraseña es incorrecta\"", HttpStatus.FORBIDDEN);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<String>("\"error\": \"El usuario no existe\"", HttpStatus.NOT_FOUND);
        }
        
    }
    
    public record UsuarioRecord(
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe ser válido")
        String email,
        
        @NotBlank(message = "La contraseña no puede estar vacía")
        String password
    ) {}
}
