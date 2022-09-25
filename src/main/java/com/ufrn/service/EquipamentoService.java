package com.ufrn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.repository.EquipamentoRepository;

@Service
public class EquipamentoService {
    
    @Autowired
    EquipamentoRepository equipamentoRepository;
}
