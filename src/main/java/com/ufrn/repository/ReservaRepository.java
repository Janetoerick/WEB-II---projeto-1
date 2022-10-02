package com.ufrn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufrn.model.Equipamento;
import com.ufrn.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    
//    @Query(value = " select re from Reserva re where :eq IN re.equipamentos")
//    public List<Reserva> findByIdEquipamento(@Param("eq") Equipamento equipamento);
}
