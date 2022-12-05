package com.ufrn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.model.ReservaIndividual;

public interface ReservaIndividualRepository extends JpaRepository<ReservaIndividual,Integer>{

    List<ReservaIndividual> findByUsuario_Id(Integer id);
    List<ReservaIndividual> findByUsuario_Login(String login);
}
