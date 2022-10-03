package com.ufrn.service;

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
//        List<Equipamento> eqs = equipamentoRepository.findBySalaDateTimeinReserva(id_sala,
//                reserva.getData(), reserva.getHorarioInicial(), reserva.getHorarioFinal());
        
           System.out.println(reserva.getData() + " - " + id_sala + " - " + qntEquipamentos);
           System.out.println(reserva.getHorarioInicial() + " - " + reserva.getHorarioFinal());
        List<Equipamento> eqs = equipamentoRepository.findBySalaDateTimeinReserva();
        
        System.out.println(eqs.size() + " -------------------------------------<");
        
        
//        if(eqs.isEmpty()) {
//            return false;
//        }
//        
//        Set<Equipamento> temp = new HashSet<Equipamento>();
//        
//        for(int i = 0; qntEquipamentos > 0; i++) {
//            temp.add(eqs.get(i));
//            qntEquipamentos--;
//        }
//        reserva.setEquipamentos(temp);
//        
//        reservaRepository.save(reserva);
//        return true;
        return false;
    }
   
    
    public List<Reserva> getAllReservasByIdUser(int id){
        return reservaRepository.findByUsuario_Id(id);
    }
    
    public void deleteById(int id) {
        reservaRepository.deleteById(id);
    }
}
