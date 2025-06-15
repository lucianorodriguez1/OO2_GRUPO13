package com.oo2.grupo13.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor 
@SuperBuilder 
public abstract class Usuario implements UserDetails{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    
    private String apellido;
    
    @Column(name="email", nullable=false)
    private String email;
    
    @Column(name="password", nullable=false)
    private String password;
    
    private String fotoPerfil;
    
    @ManyToOne
    @JoinColumn(name="rol_id", nullable=false)
    private UsuarioRol rol;
    
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    
	@UpdateTimestamp
	private LocalDateTime fechaActualizacion;
    
    public Usuario(String nombre, String apellido, String email, String password, String fotoPerfil, UsuarioRol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.fotoPerfil = fotoPerfil;
        this.rol = rol;
    }
    
}
