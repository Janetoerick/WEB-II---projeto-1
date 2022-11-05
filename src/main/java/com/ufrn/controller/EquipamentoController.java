package com.ufrn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ufrn.model.Equipamento;
import com.ufrn.service.EquipamentoService;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

    @Autowired
    private EquipamentoService service;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipamento save( @RequestBody Equipamento eq ){
        Equipamento equipamento = service.save(eq);
        return equipamento;
    }
    
    @GetMapping("{id}")
    public Equipamento getById( @PathVariable Integer id ){
        return service.findById(id);
    }
    
    @DeleteMapping
    public void removeById(@RequestBody Integer id) {
        service.deleteById(id);
    }
}
