package com.ufrn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.DTO.EquipamentoDTO;
import com.ufrn.DTO.EquipamentoReturnDTO;
import com.ufrn.DTO.SalaDTO;
import com.ufrn.exception.RegraNegocioException;
import com.ufrn.model.Equipamento;
import com.ufrn.model.Sala;
import com.ufrn.repository.EquipamentoRepository;
import com.ufrn.repository.SalaRepository;

@Service
public class EquipamentoService {

    @Autowired
    EquipamentoRepository repository;

    @Autowired
    SalaRepository salaRepository;

    public EquipamentoReturnDTO save(EquipamentoDTO eq) {

        Sala s = salaRepository.findById(eq.getSala())
                .orElseThrow(() -> new RegraNegocioException("Codigo da sala inexistente"));

        if (eq.getDescricao() == null)
            throw new RegraNegocioException("Equipamento sem campo 'descricao'");

        for (Equipamento e : repository.findAll()) {
            if (eq.getCodigo() == e.getCodigo() && eq.getSala() == e.getSala().getId()) {
                throw new RegraNegocioException("Código de equipamento já existente na sala");
            }
        }

        Equipamento e = new Equipamento();
        e.setCodigo(eq.getCodigo());
        e.setDescricao(eq.getDescricao());
        e.setSala(s);

        Equipamento eqm = repository.save(e);
        
        EquipamentoReturnDTO eqReturn = new EquipamentoReturnDTO();
        eqReturn.setId(eqm.getId());
        eqReturn.setCodigo(eq.getCodigo());
        eqReturn.setDescricao(eq.getDescricao());
        SalaDTO sala = new SalaDTO();
        sala.setNome(s.getNome());
        sala.setLocal(s.getLocal());
        eqReturn.setSala(sala);
        return eqReturn;
    }

    public EquipamentoReturnDTO findById(Integer id) {
        Equipamento e =  repository
                .findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nao existe equipamento com id=" + id));
        
        EquipamentoReturnDTO eqDTO = new EquipamentoReturnDTO();
        eqDTO.setId(id);
        eqDTO.setCodigo(e.getCodigo());
        eqDTO.setDescricao(e.getDescricao());
        SalaDTO sala = new SalaDTO();
        sala.setNome(e.getSala().getNome());
        sala.setLocal(e.getSala().getLocal());
        eqDTO.setSala(sala);
        
        return eqDTO;
    }

    public List<EquipamentoReturnDTO> findAll() {
        
        List<EquipamentoReturnDTO> list_return = new ArrayList<>();
        
        List<Equipamento> eqs = repository.findAll();
        
        for (Equipamento equipamento : eqs) {
            EquipamentoReturnDTO e = new EquipamentoReturnDTO();
            e.setId(equipamento.getId());
            e.setCodigo(equipamento.getCodigo());
            e.setDescricao(equipamento.getDescricao());
            SalaDTO sala = new SalaDTO();
            sala.setNome(equipamento.getSala().getNome());
            sala.setLocal(equipamento.getSala().getLocal());
            e.setSala(sala);
            
            list_return.add(e);
        }
        
        return list_return;
    }

    public void deleteById(Integer id) {
        repository
                .findById(id)
                .map(e -> {
                    repository.delete(e);
                    return 0;
                })
                .orElseThrow(() -> new RegraNegocioException("Nao existe equipamento com id=" + id));
    }

    public EquipamentoReturnDTO update(Integer id, EquipamentoDTO eq) {

        Equipamento e = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("id inexistente no banco"));

        Sala s = salaRepository.findById(eq.getSala())
                .orElseThrow(() -> new RegraNegocioException("Codigo da sala inexistente"));

        if (eq.getDescricao() == null)
            throw new RegraNegocioException("Equipamento sem campo 'descricao'");

        for (Equipamento equipamento : repository.findAll()) {
            if (eq.getCodigo() == equipamento.getCodigo() && eq.getSala() == equipamento.getSala().getId()
                    && equipamento.getId() != id) {
                throw new RegraNegocioException("Código de equipamento já existente na sala");
            }
        }

        e.setCodigo(eq.getCodigo());
        e.setDescricao(eq.getDescricao());
        e.setSala(s);

        repository.save(e);

        EquipamentoReturnDTO eqReturn = new EquipamentoReturnDTO();
        eqReturn.setId(id);
        eqReturn.setCodigo(eq.getCodigo());
        eqReturn.setDescricao(eq.getDescricao());
        SalaDTO sala = new SalaDTO();
        sala.setNome(s.getNome());
        sala.setLocal(s.getLocal());
        eqReturn.setSala(sala);
        return eqReturn;

    }
}
