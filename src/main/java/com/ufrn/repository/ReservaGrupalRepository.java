package com.ufrn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufrn.model.ReservaGrupal;

public interface ReservaGrupalRepository extends JpaRepository<ReservaGrupal,Integer>{

    //@Query(" select r from ReservaGrupal r where r.turma.id = :id")
    List<ReservaGrupal> findByTurma_Id(Integer id);
    
    //@Query(" select r from ReservaGrupal r where r.professor.id = :id")
    List<ReservaGrupal> findByTurma_Professor_Id(Integer id);
    
    List<ReservaGrupal> findBySala_Id(Integer id);
}
