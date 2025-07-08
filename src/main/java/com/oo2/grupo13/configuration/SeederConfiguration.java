package com.oo2.grupo13.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.oo2.grupo13.entities.Area;
import com.oo2.grupo13.entities.Cliente;
import com.oo2.grupo13.entities.Especialidad;
import com.oo2.grupo13.entities.Soporte;
import com.oo2.grupo13.entities.TURNO;
import com.oo2.grupo13.entities.Usuario;
import com.oo2.grupo13.entities.UsuarioRol;
import com.oo2.grupo13.enums.ROL;
import com.oo2.grupo13.repositories.IAreaRepository;
import com.oo2.grupo13.repositories.IEspecialidadRepository;
import com.oo2.grupo13.repositories.IUsuarioRepository;
import com.oo2.grupo13.repositories.IUsuarioRolRepository;


import java.time.LocalDate;


@Component
public class SeederConfiguration implements CommandLineRunner {

	private static final String passwordGeneric = "foo1234";
	private final IUsuarioRepository userRepository;
	private final IUsuarioRolRepository roleRepository;
	private final IEspecialidadRepository especialidadRepository;
	private final IAreaRepository areaRepository;

	public SeederConfiguration(IUsuarioRepository userRepository, IUsuarioRolRepository roleRepository,
			IEspecialidadRepository especialidadRepository, IAreaRepository areaRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.especialidadRepository = especialidadRepository;
		this.areaRepository = areaRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadRoles();
		loadUsers();
	}

	private void loadUsers() {
		if (userRepository.count() == 0) {
			loadUserAdmin();
			loadUserCliente();
			loadUserSoporte();
			loadEspecialidades(); 
		    loadAreas();  
		}
	}

	private void loadUserCliente() {
		userRepository.save(buildUserCliente("cliente", "cliente", "cliente@gmail.com", passwordGeneric));
	}
	private void loadEspecialidades() {
	    if (especialidadRepository.count() == 0) {
	        especialidadRepository.save(new Especialidad("Redes"));
	        especialidadRepository.save(new Especialidad("Bases de Datos"));
	        especialidadRepository.save(new Especialidad("Seguridad Informática"));
	    }
	}

	private void loadAreas() {
	    if (areaRepository.count() == 0) {
	        areaRepository.save(new Area("Ventas"));
	        areaRepository.save(new Area("Soporte Técnico"));
	        areaRepository.save(new Area("Recursos Humanos"));
	    }
	}


	private Usuario buildUserCliente(String nombre, String apellido, String email, String password) {
		return Cliente.builder().nombre(nombre).apellido(apellido).email(email).password(encryptPassword(password))
				.rol(roleRepository.findByRol(ROL.CLIENTE).get()).build();
	}

	private void loadUserAdmin() {
		userRepository.save(buildUserAdmin("admin", "admin", "admin@gmail.com", passwordGeneric));
	}

	private Usuario buildUserAdmin(String nombre, String apellido, String email, String password) {
		return Soporte.builder().nombre(nombre).apellido(apellido).email(email).password(encryptPassword(password))
				.rol(roleRepository.findByRol(ROL.ADMIN).get()).cuil("20-12345678-9").fechaIngreso(LocalDate.now())
				.turno(TURNO.TARDE).build();
	}

	private void loadUserSoporte() {
		userRepository.save(buildUserSoporte("soporte", "soporte", "soporte@gmail.com", passwordGeneric));
	}

	private Usuario buildUserSoporte(String nombre, String apellido, String email, String password) {
		return Soporte.builder().nombre(nombre).apellido(apellido).email(email).password(encryptPassword(password))
				.rol(roleRepository.findByRol(ROL.SOPORTE).get()).cuil("27-34567890-1")
				.fechaIngreso(LocalDate.of(2023, 3, 15)).turno(TURNO.TARDE).build();
	}

	private void loadRoles() {
		if (roleRepository.count() == 0) {
			roleRepository.save(buildRole(ROL.ADMIN));
			roleRepository.save(buildRole(ROL.CLIENTE));
			roleRepository.save(buildRole(ROL.SOPORTE));
		}
	}

	private UsuarioRol buildRole(ROL rol) {
		return UsuarioRol.builder().rol(rol).build();
	}

	private String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(7);
		return passwordEncoder.encode(password);
	}
}