package com.ufrn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.model.Turma;

public interface TurmaRepository extends JpaRepository<Turma,Integer>{

    List<Turma> findByProfessor_Login(String login);
}
