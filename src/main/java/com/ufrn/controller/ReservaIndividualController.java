package com.ufrn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.DTO.EquipamentoReservaDTO;
import com.ufrn.DTO.HorarioDTO;
import com.ufrn.DTO.ReservaIndividualDTO;
import com.ufrn.enums.tipoReserva;
import com.ufrn.exception.ReservaInvalidaException;
import com.ufrn.model.ReservaGrupal;
import com.ufrn.model.ReservaIndividual;
import com.ufrn.service.ReservaGrupalService;
import com.ufrn.service.ReservaIndividualService;


@RestController
@RequestMapping("/reservaIndividual")
public class ReservaIndividualController {

    
    
    @Autowired
    private ReservaIndividualService serviceIndividual;
    
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaIndividual save( @RequestBody ReservaIndividualDTO reserva ){
        return serviceIndividual.save(reserva);
    }
    
    @GetMapping("/aluno/{id}")
    public List<ReservaIndividual> findByAlunoId( @PathVariable Integer id){
        return serviceIndividual.findByAlunoId(id);
    }
    
    @PutMapping("{id}")
    public ReservaIndividual update( @PathVariable Integer id, @RequestBody HorarioDTO horario ){
        return serviceIndividual.update(id, horario);
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        serviceIndividual.deleteById(id);
    }
    
    
}
