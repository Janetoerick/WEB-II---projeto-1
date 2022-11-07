package com.ufrn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.DTO.HorarioDTO;
import com.ufrn.DTO.ReservaGrupalDTO;
import com.ufrn.exception.RegraNegocioException;
import com.ufrn.exception.ReservaInvalidaException;
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
                .orElseThrow(() -> new RegraNegocioException("Sala inexistente com o id " + reserva.getSala())));
        rg.setTurma(turmaRepository.findById(reserva.getTurma())
                .orElseThrow(() -> new RegraNegocioException("turma inexistente com o id " + reserva.getTurma())));

        repository.save(rg);
        return reserva;
    }

    public List<ReservaGrupal> findBySalaId(Integer id) {

        return repository
                .findBySala_Id(id);
    }

    public List<ReservaGrupal> findByTurmaId(Integer id) {

        return repository
                .findByTurma_Id(id);
    }

    public ReservaGrupal update(Integer id, HorarioDTO horario) {

        ReservaGrupal res = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("id nao existente nas reservas individuais"));

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
