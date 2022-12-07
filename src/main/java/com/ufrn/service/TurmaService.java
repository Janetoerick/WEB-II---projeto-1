package com.ufrn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.DTO.TurmaBasicDTO;
import com.ufrn.DTO.TurmaDTO;
import com.ufrn.DTO.UserDTO;
import com.ufrn.exception.RegraNegocioException;
import com.ufrn.exception.TurmaNotExistException;
import com.ufrn.exception.UserNameNotFoundException;
import com.ufrn.exception.UsuarioNotExistException;
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
                .findByLogin(turma.getProfessor())
                .orElseThrow(() -> new UsuarioNotExistException());
        
        Turma t = new Turma();
        t.setDescricao(turma.getDescricao());
        t.setProfessor(professor);
        t.setAlunos(new ArrayList<>());
        
        for (String aluno : turma.getAlunos()) {
            alunoRepository.findByLogin(aluno)
            .map(a -> {
                List<UsuarioAluno> list_aluno = t.getAlunos();
                list_aluno.add((UsuarioAluno) a);
                t.setAlunos(list_aluno);
                return 0;
            })
            .orElseThrow(() -> new UserNameNotFoundException());
        }
        repository.save(t);
        return turma;
    }
    
    public Turma findById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new TurmaNotExistException());
    }
    
    
    public List<Turma> findByProfessorLogin(String login) {
        return repository
                .findByProfessor_Login(login);
    }
    
    
    public List<TurmaBasicDTO> findAll(){
        
        List<TurmaBasicDTO> list_return = new ArrayList<>();
        
        for (Turma turma: repository.findAll()) {
            TurmaBasicDTO t = new TurmaBasicDTO();
            t.setId(turma.getId());
            t.setDescricao(turma.getDescricao());
            t.setProfessor(turma.getProfessor().getLogin());
            List<String> list_logins = new ArrayList<>();
            for (UsuarioAluno log : turma.getAlunos()) {
                list_logins.add(log.getLogin());
            }
            t.setAlunos(list_logins);
            
            list_return.add(t);
        }
        
        return list_return;
    }
    
    public boolean deleteById(Integer id) {
       return repository
        .findById(id)
        .map(turma -> {
            repository.delete(turma);
            return true;
        })
        .orElseThrow(() -> new TurmaNotExistException());
    }
    
    public TurmaBasicDTO update(Integer id, TurmaBasicDTO turma) {
        
        if(turma.getDescricao() == null || turma.getProfessor() == null)
            throw new RegraNegocioException("Campos em falta!");
        
        UsuarioProfessor professor = (UsuarioProfessor) professorRepository
                .findByLogin(turma.getProfessor())
                .orElseThrow(() -> new UserNameNotFoundException());
        
        Turma t = repository.findById(id).orElseThrow(() -> new TurmaNotExistException());
        
        
        t.setDescricao(turma.getDescricao());
        t.setProfessor(professor);
        t.setAlunos(new ArrayList<>());
        
        for (String aluno : turma.getAlunos()) {
            alunoRepository.findByLogin(aluno)
            .map(a -> {
                List<UsuarioAluno> list_aluno = t.getAlunos();
                list_aluno.add((UsuarioAluno) a);
                t.setAlunos(list_aluno);
                return 0;
            })
            .orElseThrow(() -> new UserNameNotFoundException());
        }
        repository.save(t);
        return turma;
        
    }
}
