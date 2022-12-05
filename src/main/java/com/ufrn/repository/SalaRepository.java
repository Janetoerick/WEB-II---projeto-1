package com.ufrn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.model.Sala;

public interface SalaRepository extends JpaRepository<Sala ,Integer>{
    
    List<Sala> findByLocal(String local);

}
