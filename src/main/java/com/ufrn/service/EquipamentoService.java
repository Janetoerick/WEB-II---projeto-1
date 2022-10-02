package com.ufrn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.model.Equipamento;
import com.ufrn.model.Reserva;
import com.ufrn.model.Sala;
import com.ufrn.repository.EquipamentoRepository;
import com.ufrn.repository.ReservaRepository;

@Service
public class EquipamentoService {
    
    @Autowired
    EquipamentoRepository equipamentoRepository;
    
    @Autowired
    ReservaRepository reservaRepository;
    
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
    
    public Equipamento getById(Integer id){
        return equipamentoRepository.findById(id).map(eq -> {
            return eq;
        }).orElseThrow(() -> null);
    }
    
    public List<Equipamento> getBySala(Sala sala){
        return equipamentoRepository.findBySala(sala);
    }
    
    public List<Equipamento> getAllEquipamentos(){
        return equipamentoRepository.findAll();
    }
    
    public void removeById(Integer id) {
//        Optional<Equipamento> temp = equipamentoRepository.findById(id);
//        List<Reserva> res = reservaRepository.findByIdEquipamento(temp.map(eq -> {
//            return eq;
//        }).orElseThrow(() -> null));
//        
//        for(Reserva r: res) {
//            reservaRepository.deleteById(r.getId());
//        }
        
        equipamentoRepository.deleteById(id);
    }
    
    
}
