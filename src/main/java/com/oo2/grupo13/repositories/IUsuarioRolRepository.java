package com.oo2.grupo13.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo13.entities.UsuarioRol;

@Repository("usuarioRolRepository")
public interface IUsuarioRolRepository extends JpaRepository<UsuarioRol, Integer> {

}
