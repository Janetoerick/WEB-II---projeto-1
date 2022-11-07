package com.ufrn.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.DTO.HorarioDTO;
import com.ufrn.exception.RegraNegocioException;
import com.ufrn.model.Equipamento;
import com.ufrn.model.ReservaGrupal;
import com.ufrn.model.ReservaIndividual;
import com.ufrn.model.Sala;
import com.ufrn.repository.ReservaGrupalRepository;
import com.ufrn.repository.ReservaIndividualRepository;
import com.ufrn.repository.SalaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaGrupalRepository repositoryGrupal;
    
    @Autowired
    private ReservaIndividualRepository repositoryIndividual;
    
    @Autowired
    private SalaRepository repositorySala;
    
    public void verificarHorario(HorarioDTO horario) {
        
        if(horario.getData() == null || horario.getHorarioFinal() == null || horario.getHorarioInicial() == null)
            throw new RegraNegocioException("Dados de horario ou data inexistentes!");
            
        if(horario.getData().isBefore(LocalDate.now()))
            throw new RegraNegocioException("Data irregular!");
        
        if(horario.getHorarioInicial().isAfter(horario.getHorarioFinal()))
            throw new RegraNegocioException("Horarios irregulares!");
    }
    
    public boolean verificarDisponibilidade(HorarioDTO horario, Integer equipamento_id) {
        
        verificarHorario(horario);
        
        
        List<ReservaIndividual> reserva_i = repositoryIndividual.findAll();
        List<ReservaIndividual> reserva_i2 = new ArrayList<>();
        
        for (ReservaIndividual ri : reserva_i) {
            if(ri.getData().equals(horario.getData()) && !(ri.getHorarioInicial().isAfter(horario.getHorarioFinal())
                    || ri.getHorarioFinal().isBefore(horario.getHorarioInicial()))) {
                reserva_i2.add(ri);
            }
        }
        
        
        
        for (ReservaIndividual ri : reserva_i2) {
            for (Equipamento e : ri.getEquipamentos()) {
                if(e.getId() == equipamento_id) {
                    return false;
                }
            }
        }
        
        
        List<ReservaGrupal> reserva_g = repositoryGrupal.findAll();
        List<ReservaGrupal> reserva_g2 = new ArrayList<>();
        
        for (ReservaGrupal rg : reserva_g) {
            if(rg.getData().equals(horario.getData()) && !(rg.getHorarioInicial().isAfter(horario.getHorarioFinal())
                    || rg.getHorarioFinal().isBefore(horario.getHorarioInicial()))) {
                reserva_g2.add(rg);
            }
        }
        
        List<Integer> listSalas = new ArrayList<>();
        for (ReservaGrupal rg : reserva_g2) {
            if(!listSalas.contains(rg.getSala().getId())) {
                Sala sala = repositorySala
                        .findById(rg.getSala().getId())
                        .orElseThrow(() -> new RegraNegocioException("Erro no servidor!!"));
                for (Equipamento e : sala.getEquipamentos()) {
                    if(e.getId() == equipamento_id) {
                        System.out.println("entrou no segundo !!");
                        return false;
                    }
                        
                }
                listSalas.add(rg.getSala().getId());
            }
            
        }
        
        return true;
    }
}
