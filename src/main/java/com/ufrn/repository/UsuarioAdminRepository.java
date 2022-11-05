package com.ufrn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.model.Usuario;
import com.ufrn.model.UsuarioAdmin;

public interface UsuarioAdminRepository extends JpaRepository<UsuarioAdmin,Integer>{

    Optional<Usuario> findByLogin(String login);
}
