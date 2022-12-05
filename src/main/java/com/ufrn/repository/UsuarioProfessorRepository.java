package com.ufrn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.model.UsuarioProfessor;

public interface UsuarioProfessorRepository extends JpaRepository<UsuarioProfessor,Integer>{

    Optional<UsuarioProfessor> findByLogin(String login);
}
