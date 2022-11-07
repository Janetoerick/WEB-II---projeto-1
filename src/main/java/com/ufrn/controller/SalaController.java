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

import com.ufrn.model.Sala;
import com.ufrn.service.SalaService;

@RestController
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    private SalaService service;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sala save( @RequestBody Sala sala ){
        Sala s = service.save(sala);
        return s;
    }
    
    @GetMapping("{id}")
    public Sala getById( @PathVariable Integer id ){
        return service.findById(id);
    }
    
    @GetMapping
    public List<Sala> getAll(){
        return service.findAll();
    }
    
    @PutMapping("{id}")
    public Sala update( @PathVariable Integer id, @RequestBody Sala sala ){
        Sala s = service.update(id, sala);
        return s;
    }
    
    @DeleteMapping
    public void removeById(@RequestBody Integer id) {
        service.deleteById(id);
    }
}
