package com.oo2.grupo13.controllers.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo13.services.IUsuarioService;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody UsuarioRecord usuario) {
        
        return new ResponseEntity<String>(usuarioService.authenticate(usuario.email(), usuario.password()), HttpStatus.OK);
    }
    
    public record UsuarioRecord(
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe ser válido")
        String email,
        
        @NotBlank(message = "La contraseña no puede estar vacía")
        String password
    ) {}
}
