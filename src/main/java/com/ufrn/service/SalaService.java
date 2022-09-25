package com.ufrn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.repository.SalaRepository;

@Service
public class SalaService {
    
    @Autowired
    SalaRepository salaRepository;

}
