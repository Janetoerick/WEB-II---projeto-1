package com.ufrn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.DTO.HorarioDTO;
import com.ufrn.DTO.ReservaGrupalAllDTO;
import com.ufrn.DTO.ReservaGrupalDTO;
import com.ufrn.exception.RegraNegocioException;
import com.ufrn.exception.ReservaInvalidaException;
import com.ufrn.exception.ReservaNotExistException;
import com.ufrn.exception.SalaNotExistException;
import com.ufrn.exception.TurmaNotExistException;
import com.ufrn.exception.UserNotExistException;
import com.ufrn.model.Equipamento;
import com.ufrn.model.ReservaGrupal;
import com.ufrn.model.ReservaIndividual;
import com.ufrn.repository.EquipamentoRepository;
import com.ufrn.repository.ReservaGrupalRepository;
import com.ufrn.repository.SalaRepository;
import com.ufrn.repository.TurmaRepository;

@Service
public class ReservaGrupalService {

    @Autowired
    private ReservaGrupalRepository repository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ReservaService service;

    public ReservaGrupalDTO save(ReservaGrupalDTO reserva) {

        List<ReservaGrupal> verify_turma = repository.findByTurma_Id(reserva.getTurma());
        if(verify_turma.size() > 0)
            throw new RegraNegocioException("A turma ja tem uma reserva");
        
        HorarioDTO horario = new HorarioDTO(reserva.getData(), reserva.getHorarioInicial(), reserva.getHorarioFinal());

        List<Equipamento> equipamentos = equipamentoRepository.findBySala_Id(reserva.getSala());
        for (Equipamento equipamento : equipamentos) {
            if (!service.verificarDisponibilidade(horario, equipamento.getId())) {
                throw new ReservaInvalidaException();
            }
        }

        ReservaGrupal rg = new ReservaGrupal();
        rg.setData(reserva.getData());
        rg.setHorarioInicial(reserva.getHorarioInicial());
        rg.setHorarioFinal(reserva.getHorarioFinal());
        rg.setSala(salaRepository.findById(reserva.getSala())
                .orElseThrow(() -> new SalaNotExistException()));
        rg.setTurma(turmaRepository.findById(reserva.getTurma())
                .orElseThrow(() -> new TurmaNotExistException()));

        repository.save(rg);
        return reserva;
    }
    
    public List<ReservaGrupalAllDTO> findAll() {
        List<ReservaGrupal> list_reserva = repository.findAll();
        List<ReservaGrupalAllDTO> list_return = new ArrayList<>();
        
        for (ReservaGrupal r : list_reserva) {
            ReservaGrupalAllDTO reserva = new ReservaGrupalAllDTO();
            reserva.setId(r.getId());
            reserva.setData(r.getData());
            reserva.setHorarioInicial(r.getHorarioInicial());
            reserva.setHorarioFinal(r.getHorarioFinal());
            reserva.setSala(r.getSala().getId());
            reserva.setTurma(r.getTurma().getId());
            
            list_return.add(reserva);
        }
        
        
        return list_return;
    }

    public List<ReservaGrupal> findBySalaId(Integer id) {

        salaRepository.findById(id).orElseThrow(() -> new SalaNotExistException());
        
        return repository
                .findBySala_Id(id);
    }

    public List<ReservaGrupal> findByTurmaId(Integer id) {

        turmaRepository.findById(id).orElseThrow(() -> new TurmaNotExistException());
        
        return repository
                .findByTurma_Id(id);
    }

    public ReservaGrupal update(Integer id, HorarioDTO horario) {

        ReservaGrupal res = repository.findById(id)
                .orElseThrow(() -> new ReservaNotExistException());

        if (horario.getData() != null)
            res.setData(horario.getData());

        if (horario.getHorarioInicial() != null)
            res.setHorarioInicial(horario.getHorarioInicial());

        if (horario.getHorarioFinal() != null)
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
