package com.oo2.grupo13.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo13.entities.UsuarioRol;
import com.oo2.grupo13.enums.ROL;

@Repository("usuarioRolRepository")
public interface IUsuarioRolRepository extends JpaRepository<UsuarioRol, Integer> {
    Optional<UsuarioRol> findByRol(ROL rol);

}
