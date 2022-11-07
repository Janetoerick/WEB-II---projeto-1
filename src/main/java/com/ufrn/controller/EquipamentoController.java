package com.ufrn.controller;

import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ufrn.DTO.EquipamentoDTO;
import com.ufrn.DTO.EquipamentoReturnDTO;
import com.ufrn.model.Equipamento;
import com.ufrn.service.EquipamentoService;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

    @Autowired
    private EquipamentoService service;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipamentoReturnDTO save( @RequestBody EquipamentoDTO eq ){
        EquipamentoReturnDTO equipamento = service.save(eq);
        return equipamento;
    }
    
    @GetMapping("{id}")
    public EquipamentoReturnDTO getById( @PathVariable Integer id ){
        return service.findById(id);
    }
    
    @GetMapping
    public List<EquipamentoReturnDTO> getAll(){
        return service.findAll();
    }
    
    @PutMapping("{id}")
    public EquipamentoReturnDTO update( @PathVariable Integer id, @RequestBody EquipamentoDTO eq ){
        EquipamentoReturnDTO equipamento = service.update(id, eq);
        return equipamento;
    }
    
    @DeleteMapping
    public void removeById(@RequestBody Integer id) {
        service.deleteById(id);
    }
}
