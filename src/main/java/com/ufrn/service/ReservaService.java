package com.ufrn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.repository.ReservaRepository;

@Service
public class ReservaService {
    
    @Autowired
    ReservaRepository reservaRepository;
}
