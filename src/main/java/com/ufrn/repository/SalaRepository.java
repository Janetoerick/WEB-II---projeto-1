package com.ufrn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer>{
    
}
