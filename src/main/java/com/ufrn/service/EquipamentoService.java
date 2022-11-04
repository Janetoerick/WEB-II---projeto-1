package com.ufrn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.exception.RegraNegocioException;
import com.ufrn.model.Equipamento;
import com.ufrn.repository.EquipamentoRepository;
import com.ufrn.repository.SalaRepository;

@Service
public class EquipamentoService {

    @Autowired
    EquipamentoRepository repository;
    
    @Autowired
    SalaRepository salaRepository;
    
    public Equipamento save(Equipamento eq) {
        
        if(eq.getSala() == null || salaRepository.existsById(eq.getSala().getId()))
            throw new RegraNegocioException("Sala do equipamento nao existe no sistema");
        
        if(eq.getDescricao() == null)
            throw new RegraNegocioException("Equipamento sem campo 'descricao'");
        
        for (Equipamento e : repository.findAll()) {
            if(eq.getCodigo() == e.getCodigo() && eq.getSala().getId() == e.getSala().getId()) {
                throw new RegraNegocioException("Código de equipamento já existente na sala");
            }
        }
        
        repository.save(eq);
        return eq;
    }
    
    public Equipamento findById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nao existe equipamento com id=" + id));
    }
    
    public List<Equipamento> findAll(){
        return repository.findAll();
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
}
