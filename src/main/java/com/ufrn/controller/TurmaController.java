package com.ufrn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.model.Turma;
import com.ufrn.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaService service;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Turma save( @RequestBody Turma turma ){
        Turma t = service.save(turma);
        return t;
    }
    
    @GetMapping("{id}")
    public Turma getById( @PathVariable Integer id ){
        return service.findById(id);
    }
    
    @DeleteMapping
    public void removeById(@RequestBody Integer id) {
        service.deleteById(id);
    }
}
