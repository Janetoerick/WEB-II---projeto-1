package com.ufrn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.model.Equipamento;
import com.ufrn.model.Sala;
import com.ufrn.repository.EquipamentoRepository;
import com.ufrn.repository.SalaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {
    
    @Autowired
    SalaRepository salaRepository;

    @Autowired
    EquipamentoRepository equipamentoRepository;

    public Sala add(Sala sala){
        return salaRepository.save(sala);
    }

    public List<Sala> getAllSalas(){
        return salaRepository.findAll();
    }
    
    public Sala getById(int id) {
        return salaRepository.findById(id).map(sala -> {
            return sala;
        }).orElseThrow(() -> null);   
    }
    
    public void deleteById(Integer id) {
        Optional<Sala> temp = salaRepository.findById(id);
        List<Equipamento> eq = equipamentoRepository.findBySala(temp.map(sala -> {
            return sala;
        }).orElseThrow(() -> null));
        
        for(Equipamento e: eq) {
            equipamentoRepository.deleteById(e.getId());
        }
        salaRepository.deleteById(id);
    }

}
