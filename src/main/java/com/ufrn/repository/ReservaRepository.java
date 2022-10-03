package com.ufrn.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufrn.model.Equipamento;
import com.ufrn.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
   
    
    List<Reserva> findByEquipamentos_Codigo(int codigo);
    
    List<Reserva> findByUsuario_Id(int id);
}
