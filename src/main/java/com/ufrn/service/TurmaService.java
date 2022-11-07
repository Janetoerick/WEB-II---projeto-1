package com.ufrn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.DTO.TurmaDTO;
import com.ufrn.DTO.UserDTO;
import com.ufrn.exception.RegraNegocioException;
import com.ufrn.model.Turma;
import com.ufrn.model.UsuarioAluno;
import com.ufrn.model.UsuarioProfessor;
import com.ufrn.repository.TurmaRepository;
import com.ufrn.repository.UsuarioAlunoRepository;
import com.ufrn.repository.UsuarioProfessorRepository;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository repository;
    
    @Autowired
    private UsuarioProfessorRepository professorRepository;
    
    @Autowired
    private UsuarioAlunoRepository alunoRepository;
    
    public TurmaDTO save(TurmaDTO turma) {
        if(turma.getDescricao() == null || turma.getProfessor() == null)
            throw new RegraNegocioException("Campos em falta!");
        
        UsuarioProfessor professor = (UsuarioProfessor) professorRepository
                .findByLogin(turma.getProfessor().getLogin())
                .orElseThrow(() -> new RegraNegocioException("Professor nao existe!"));
        
        Turma t = new Turma();
        t.setDescricao(turma.getDescricao());
        t.setProfessor(professor);
        t.setAlunos(new ArrayList<>());
        
        for (UserDTO aluno : turma.getAlunos()) {
            alunoRepository.findByLogin(aluno.getLogin())
            .map(a -> {
                List<UsuarioAluno> list_aluno = t.getAlunos();
                list_aluno.add((UsuarioAluno) a);
                t.setAlunos(list_aluno);
                return 0;
            })
            .orElseThrow(() -> new RegraNegocioException("Login de aluno nao existe! " + aluno.getLogin()));
        }
        repository.save(t);
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
    
    public boolean deleteById(Integer id) {
       return repository
        .findById(id)
        .map(turma -> {
            repository.delete(turma);
            return true;
        })
        .orElseThrow(() -> new RegraNegocioException("Turma com id " + id + " nao existente"));
    }
    
    public TurmaDTO update(Integer id, TurmaDTO turma) {
        
        if(turma.getDescricao() == null || turma.getProfessor() == null)
            throw new RegraNegocioException("Campos em falta!");
        
        UsuarioProfessor professor = (UsuarioProfessor) professorRepository
                .findByLogin(turma.getProfessor().getLogin())
                .orElseThrow(() -> new RegraNegocioException("Professor nao existe!"));
        
        Turma t = repository.findById(id).orElseThrow(() -> new RegraNegocioException("Id inexistente no banco"));
        
        
        t.setDescricao(turma.getDescricao());
        t.setProfessor(professor);
        t.setAlunos(new ArrayList<>());
        
        for (UserDTO aluno : turma.getAlunos()) {
            alunoRepository.findByLogin(aluno.getLogin())
            .map(a -> {
                List<UsuarioAluno> list_aluno = t.getAlunos();
                list_aluno.add((UsuarioAluno) a);
                t.setAlunos(list_aluno);
                return 0;
            })
            .orElseThrow(() -> new RegraNegocioException("Login de aluno nao existe! " + aluno.getLogin()));
        }
        repository.save(t);
        return turma;
        
    }
}
