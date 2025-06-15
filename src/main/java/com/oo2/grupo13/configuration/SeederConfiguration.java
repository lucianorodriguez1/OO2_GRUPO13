package com.oo2.grupo13.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.Usuario;
import com.oo2.grupo13.entities.UsuarioRol;
import com.oo2.grupo13.enums.ROL;
import com.oo2.grupo13.repositories.IUsuarioRepository;
import com.oo2.grupo13.repositories.IUsuarioRolRepository;

import java.util.Set;

@Component
public class SeederConfiguration implements CommandLineRunner {

    private static final String passwordGeneric = "foo1234";

    private final IUsuarioRepository userRepository;

    private final IUsuarioRolRepository roleRepository;

    public SeederConfiguration(IUsuarioRepository userRepository, IUsuarioRolRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
        loadUsers();
    }

    private void loadUsers() {
        if (userRepository.count() == 0){
            loadUserAdmin();
            loadUserCliente();
            loadUserSoporte();

        }
    }

    private void loadUserCliente() {
        userRepository.save(buildUserCliente("cliente","cliente","cliente@gmail.com",passwordGeneric));
    }

    private Usuario buildUserCliente(String nombre, String apellido, String email, String password) {
        return Cliente.builder()
        		.nombre(nombre)
                .apellido(apellido)
                .email(email)
                .password(encryptPassword(password))
                .rol(roleRepository.findByRol(ROL.CLIENTE).get())
                .build();
    }

    private void loadUserAdmin() {
        userRepository.save(buildUserAdmin("admin","admin","admin@gmail.com",passwordGeneric));
    }

    private Usuario buildUserAdmin(String nombre, String apellido, String email, String password) {
        return Soporte.builder()
        		  .nombre(nombre)
                  .apellido(apellido)
                  .email(email)
                  .password(encryptPassword(password))
                  .rol(roleRepository.findByRol(ROL.ADMIN).get())
                  .build();
    }
    
    private void loadUserSoporte() {
        userRepository.save(buildUserSoporte("soporte","soporte","soporte@gmail.com",passwordGeneric));
    }


    private Usuario buildUserSoporte(String nombre, String apellido, String email, String password) {
        return Soporte.builder()
                .nombre(nombre)
                .apellido(apellido)
                .email(email)
                .password(encryptPassword(password))
                .rol(roleRepository.findByRol(ROL.SOPORTE).get())
                .build();
    }

    
    private void loadRoles() {
        if (roleRepository.count() == 0){
            roleRepository.save(buildRole(ROL.ADMIN));
            roleRepository.save(buildRole(ROL.CLIENTE));
            roleRepository.save(buildRole(ROL.SOPORTE));
        }
    }

    private UsuarioRol buildRole(ROL rol) {
        return UsuarioRol.builder()
                .rol(rol)
                .build();
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(7);
        return passwordEncoder.encode(password);
    }
}