package com.ufrn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.model.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer>{
    
}
