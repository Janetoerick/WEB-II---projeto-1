package com.ufrn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.model.Equipamento;
import com.ufrn.model.Sala;
import com.ufrn.repository.EquipamentoRepository;

@Service
public class EquipamentoService {
    
    @Autowired
    EquipamentoRepository equipamentoRepository;
    
    public boolean add(Equipamento eq){
        
        //List<Equipamento> equipamentos = equipamentoRepository.findAll();
        System.out.println("entrou aqui!");
        for (Equipamento equipamento : equipamentoRepository.findAll()) {
            if(equipamento.getSala() == eq.getSala() && 
                    equipamento.getCodigo() == eq.getCodigo()) {
                return false;
            }
        }
        
        equipamentoRepository.save(eq);
        return true;
        
        
    }
    
    public List<Equipamento> getBySala(Sala sala){
        return equipamentoRepository.findBySala(sala);
    }
    
    public List<Equipamento> getAllEquipamentos(){
        return equipamentoRepository.findAll();
    }
    
    
}
