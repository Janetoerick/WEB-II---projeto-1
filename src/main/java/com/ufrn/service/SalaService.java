package com.ufrn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.model.Sala;
import com.ufrn.repository.SalaRepository;

import java.util.List;

@Service
public class SalaService {
    
    @Autowired
    SalaRepository salaRepository;


    public List<Sala> getAllSalas(){
        return salaRepository.findAll();
    }

}
