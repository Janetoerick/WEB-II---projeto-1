package com.ufrn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
}
