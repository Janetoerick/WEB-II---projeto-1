package com.ufrn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufrn.model.Equipamento;
import com.ufrn.model.Sala;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer>{
    
    List<Equipamento> findBySala(Sala sala);
    
    
//    @Query(value = " select e from equipamento e where e.codigo = %:cod% and e.sala_id = %:id% ")
//    List<Equipamento> existsCodEqFromSalaId(@Param("cod") int cod, @Param("id") Integer id);
    
    
}
