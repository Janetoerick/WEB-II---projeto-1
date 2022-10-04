package com.ufrn.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.repository.EquipamentoRepository;
import com.ufrn.repository.ReservaRepository;
import com.ufrn.model.Equipamento;
import com.ufrn.model.Reserva;

@Service
public class ReservaService {
    
    @Autowired
    ReservaRepository reservaRepository;
    
    @Autowired
    EquipamentoRepository equipamentoRepository;
    
    public List<Reserva> getAllReservas(){
        return reservaRepository.findAll();
    }
    
    public boolean add(Reserva reserva,int id_sala, int qntEquipamentos) {
        List<Equipamento> e_1 = equipamentoRepository.findBySala_Id(id_sala);
        
        if(reserva.getData().isBefore(LocalDate.now()) ||
                reserva.getHorarioInicial().isAfter(reserva.getHorarioFinal()) ||
                e_1.size() < qntEquipamentos) {
            System.out.println("entrou no primeiro");
            return false;
        }
        
        
        System.out.println(reserva.getData() + " | " +  reserva.getHorarioInicial() + " | " +  reserva.getHorarioFinal());
        List<Reserva> r_1 = new ArrayList<>();
        for(Equipamento e: e_1) {
            r_1.addAll(reservaRepository.FindByEquipamentoIdDTITF(e.getId(), reserva.getData(), reserva.getHorarioInicial(), reserva.getHorarioFinal()));
        }
        
        System.out.println("size r_1:" + r_1.size());
        Set<Equipamento> e_2 = new HashSet<>();
        for(Reserva e: r_1) {
            e_1.removeAll(equipamentoRepository.FindByReservaId(e.getId()));
        }
        e_2.addAll(e_1);
        
        System.out.println(e_2.size() + " || " + qntEquipamentos);
        if(e_2.size() > qntEquipamentos)
            e_2.remove(0);
        
        System.out.println(e_2.size() + " || " + qntEquipamentos);
        System.out.println(e_2.isEmpty());
        if(e_2.isEmpty()) {
            System.out.println("entrou no segundo");
           return false;
        }
        
        reserva.setEquipamentos(e_2);
        reservaRepository.save(reserva);
        
        for(Equipamento x: e_2) {
            System.out.println("-> " + x.getId());
        }
        
        return true;
    }
   
    
    public List<Reserva> getAllReservasByIdUser(int id){
        return reservaRepository.findByUsuario_Id(id);
    }
    
    public void deleteById(int id) {
        reservaRepository.deleteById(id);
    }
}
