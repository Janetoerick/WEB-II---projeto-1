package com.ufrn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.model.Usuario;
import com.ufrn.model.UsuarioAluno;

public interface UsuarioAlunoRepository extends JpaRepository<UsuarioAluno,Integer>{

    Optional<Usuario> findByLogin(String login);
}
