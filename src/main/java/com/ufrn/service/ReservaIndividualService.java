package com.ufrn.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.DTO.EquipamentoReservaDTO;
import com.ufrn.DTO.HorarioDTO;
import com.ufrn.DTO.ReservaIndividualAllDTO;
import com.ufrn.DTO.ReservaIndividualDTO;
import com.ufrn.exception.EquipamentoNotExistException;
import com.ufrn.exception.RegraNegocioException;
import com.ufrn.exception.ReservaInvalidaException;
import com.ufrn.exception.ReservaNotExistException;
import com.ufrn.exception.UserNotExistException;
import com.ufrn.exception.UsuarioNotExistException;
import com.ufrn.model.Equipamento;
import com.ufrn.model.ReservaIndividual;
import com.ufrn.repository.EquipamentoRepository;
import com.ufrn.repository.ReservaIndividualRepository;
import com.ufrn.repository.UsuarioAlunoRepository;

@Service
public class ReservaIndividualService {

    @Autowired
    private ReservaIndividualRepository repository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private UsuarioAlunoRepository alunoRepository;

    @Autowired
    private ReservaService service;

    public ReservaIndividual save(ReservaIndividualDTO reserva) {

        List<ReservaIndividual> verify_user = repository.findByUsuario_Id(reserva.getAluno()); 
        if(verify_user.size() > 0)
            throw new RegraNegocioException("Usuario ja tem uma reserva!");
        
        HorarioDTO horario = new HorarioDTO(reserva.getData(), reserva.getHorarioInicial(), reserva.getHorarioFinal());

        Set<Equipamento> eqs = new HashSet<>();

        for (EquipamentoReservaDTO equipamento : reserva.getEquipamentos()) {
            boolean verify = service.verificarDisponibilidade(horario, equipamento.getId()); 
            if (!verify) {
                throw new ReservaInvalidaException();
            } else {
                eqs.add(equipamentoRepository.findById(equipamento.getId()).orElseThrow(
                        () -> new EquipamentoNotExistException()));
            }
        }

        ReservaIndividual r = new ReservaIndividual();
        r.setData(reserva.getData());
        r.setHorarioInicial(reserva.getHorarioInicial());
        r.setHorarioFinal(reserva.getHorarioFinal());
        r.setEquipamentos(eqs);
        r.setUsuario(alunoRepository.findById(reserva.getAluno())
                .orElseThrow(() -> new UsuarioNotExistException()));

        repository.save(r);
        return r;
    }

    public List<ReservaIndividual> findByAlunoId(Integer id) {

        alunoRepository.findById(id).orElseThrow(() -> new UserNotExistException());
        
        return repository
                .findByUsuario_Id(id);
    }
    
    public List<ReservaIndividualAllDTO> findAll() {

        List<ReservaIndividual> list_reserva = repository.findAll();
        List<ReservaIndividualAllDTO> list_return = new ArrayList<>();
        
        for (ReservaIndividual r : list_reserva) {
            ReservaIndividualAllDTO reserva = new ReservaIndividualAllDTO();
            reserva.setId(r.getId());
            reserva.setData(r.getData());
            reserva.setHorarioInicial(r.getHorarioInicial());
            reserva.setHorarioFinal(r.getHorarioFinal());
            reserva.setAluno(r.getUsuario().getId());
            List<EquipamentoReservaDTO> list_eq = new ArrayList<>();
            for (Equipamento e : r.getEquipamentos()) {
                EquipamentoReservaDTO eq = new EquipamentoReservaDTO();
                eq.setId(e.getId());
                list_eq.add(eq);
            }
            reserva.setEquipamentos(list_eq);
            
            list_return.add(reserva);
        }
        
        return list_return;
    }

    public ReservaIndividual update(Integer id, HorarioDTO horario) {

        ReservaIndividual res = repository.findById(id).orElseThrow(() -> new ReservaNotExistException());
        
        if(horario.getData ()!= null)
            res.setData(horario.getData());
        
        if(horario.getHorarioInicial() != null)
            res.setHorarioInicial(horario.getHorarioInicial());
        
        if(horario.getHorarioFinal() != null)
            res.setHorarioFinal(horario.getHorarioFinal());

        repository.save(res);
        return res;
        

    }

    public void deleteById(Integer id) {
        repository
                .findById(id)
                .map(reserva -> {
                    repository.delete(reserva);
                    return 0;
                })
                .orElseThrow(() -> new UserNotExistException());

    }
}
