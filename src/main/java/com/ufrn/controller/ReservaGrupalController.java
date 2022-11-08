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

import com.ufrn.DTO.HorarioDTO;
import com.ufrn.DTO.ReservaGrupalAllDTO;
import com.ufrn.DTO.ReservaGrupalDTO;
import com.ufrn.model.ReservaGrupal;
import com.ufrn.model.ReservaIndividual;
import com.ufrn.service.ReservaGrupalService;

@RestController
@RequestMapping("/reservaGrupal")
public class ReservaGrupalController {

    @Autowired
    private ReservaGrupalService serviceGrupal;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaGrupalDTO save( @RequestBody ReservaGrupalDTO reserva ){
        return serviceGrupal.save(reserva);
    }
    
    @GetMapping
    public List<ReservaGrupalAllDTO> findAll(){
        return serviceGrupal.findAll();
    }
    
    @GetMapping("/sala/{id}")
    public List<ReservaGrupal> findBySalaId( @PathVariable Integer id){
        return serviceGrupal.findBySalaId(id);
    }
    
    @GetMapping("/turma/{id}")
    public List<ReservaGrupal> findByTurmaId( @PathVariable Integer id){
        return serviceGrupal.findByTurmaId(id);
    }
    
    @PutMapping("{id}")
    public ReservaGrupal update( @PathVariable Integer id, @RequestBody HorarioDTO horario ){
        return serviceGrupal.update(id, horario);
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        serviceGrupal.deleteById(id);
    }
}
