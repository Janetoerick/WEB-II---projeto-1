package com.ufrn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.exception.RegraNegocioException;
import com.ufrn.exception.SalaNotExistException;
import com.ufrn.model.Sala;
import com.ufrn.repository.SalaRepository;

@Service
public class SalaService {

    @Autowired
    SalaRepository repository;
    
    public Sala save(Sala sala) {
        if(sala.getNome() == null || sala.getLocal() == null 
                || sala.getDescricao() == null) {
            throw new RegraNegocioException("Falta de atributos no objeto Sala");
        }
        
        if(sala.getAndar() < 0)
            throw new RegraNegocioException("Numero do andar invalido");
        
        repository.save(sala);
        return sala;
    }
    
    public Sala findById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new SalaNotExistException());
    }
    
    public List<Sala> findByLocal(String local) {
        return repository
                .findByLocal(local);
    }

    
    public List<Sala> findAll(){
        return repository.findAll();
    }
    
    public void deleteById(Integer id) {
        repository
        .findById(id)
        .map(sala -> {
            repository.delete(sala);
            return 0;
        })
        .orElseThrow(() -> new SalaNotExistException());
    }
    
    public Sala update(Integer id, Sala sala) {
        
        Sala s = repository.findById(id).orElseThrow(() -> new SalaNotExistException());
                
        if(sala.getNome() == null || sala.getLocal() == null 
                || sala.getDescricao() == null) {
            throw new RegraNegocioException("Falta de atributos no objeto Sala");
        }
        
        if(sala.getAndar() < 0)
            throw new RegraNegocioException("Numero do andar invalido");
        
        s.setNome(sala.getNome());
        s.setLocal(sala.getLocal());
        s.setDescricao(sala.getDescricao());
        s.setAndar(sala.getAndar());
        
        repository.save(s);
        return s;
    }
}
