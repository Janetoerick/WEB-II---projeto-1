package com.ufrn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    
}
