package com.ufrn.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.DTO.EquipamentoReservaDTO;
import com.ufrn.DTO.HorarioDTO;
import com.ufrn.DTO.ReservaIndividualDTO;
import com.ufrn.exception.RegraNegocioException;
import com.ufrn.exception.ReservaInvalidaException;
import com.ufrn.exception.UserNotExistException;
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

        HorarioDTO horario = new HorarioDTO(reserva.getData(), reserva.getHorarioInicial(), reserva.getHorarioFinal());

        Set<Equipamento> eqs = new HashSet<>();

        for (EquipamentoReservaDTO equipamento : reserva.getEquipamentos()) {
            boolean verify = service.verificarDisponibilidade(horario, equipamento.getId()); 
            if (!verify) {
                throw new ReservaInvalidaException();
            } else {
                eqs.add(equipamentoRepository.findById(equipamento.getId()).orElseThrow(
                        () -> new RegraNegocioException("Equipamento nao encontrado com id " + equipamento.getId())));
            }
        }

        ReservaIndividual r = new ReservaIndividual();
        r.setData(reserva.getData());
        r.setHorarioInicial(reserva.getHorarioInicial());
        r.setHorarioFinal(reserva.getHorarioFinal());
        r.setEquipamentos(eqs);
        r.setUsuario(alunoRepository.findById(reserva.getAluno())
                .orElseThrow(() -> new RegraNegocioException("aluno inexistente com id " + reserva.getAluno())));

        repository.save(r);
        return r;
    }

    public List<ReservaIndividual> findByAlunoId(Integer id) {

        return repository
                .findByUsuario_Id(id);
    }

    public ReservaIndividual update(Integer id, HorarioDTO horario) {

        ReservaIndividual res = repository.findById(id).orElseThrow(() -> new RegraNegocioException("id nao existente nas reservas individuais"));
        
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
