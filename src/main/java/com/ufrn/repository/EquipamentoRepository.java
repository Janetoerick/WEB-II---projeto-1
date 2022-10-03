package com.ufrn.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufrn.model.Equipamento;
import com.ufrn.model.Reserva;
import com.ufrn.model.Sala;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer>{
    
    List<Equipamento> findBySala(Sala sala);
    
    List<Equipamento> findByReservas_Data(LocalDate data);
    //  
    @Query(value = " select e from Equipamento e join e.reservas")
    List<Equipamento> findBySalaDateTimeinReserva();
    
//    @Query(value = " select e from Equipamento e join e.reservas r where e.sala.id = :id_s and r.data = :data and (r.horarioInicial >= :hf or r.horarioFinal <= :hi)")
//    List<Equipamento> findBySalaDateTimeinReserva(@Param("id_s") int sala_id, @Param("data") LocalDate data ,@Param("hi") LocalTime horaInicio, @Param("hf") LocalTime horaFinal);
    
}
