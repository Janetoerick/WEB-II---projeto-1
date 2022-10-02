package com.ufrn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.repository.ReservaRepository;
import com.ufrn.model.Reserva;

@Service
public class ReservaService {
    
    @Autowired
    ReservaRepository reservaRepository;
    
    public List<Reserva> getAllReservas(){
        return reservaRepository.findAll();
    }
}
