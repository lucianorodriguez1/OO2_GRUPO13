package com.oo2.grupo13.services;


public interface IUsuarioService {

    String authenticate(
            String email,
            String password);

}
