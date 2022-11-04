package com.ufrn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.exception.RegraNegocioException;
import com.ufrn.model.Turma;
import com.ufrn.repository.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository repository;
    
    public Turma save(Turma turma) {
        if(turma.getDescricao() == null)
            throw new RegraNegocioException("Campo 'descricao' nao existente");
        
        repository.save(turma);
        return turma;
    }
    
    public Turma findById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new RegraNegocioException("Turma com id " + id + " nao existente"));
    }
    
    public List<Turma> findAll(){
        return repository.findAll();
    }
    
    public void deleteById(Integer id) {
        repository
        .findById(id)
        .map(turma -> {
            repository.delete(turma);
            return 0;
        })
        .orElseThrow(() -> new RegraNegocioException("Turma com id " + id + " nao existente"));
    }
}
