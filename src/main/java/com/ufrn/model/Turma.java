package com.ufrn.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String descricao;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private UsuarioProfessor professor;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "aluno_turma",
    joinColumns = @JoinColumn(name = "turma_id"),
    inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<UsuarioAluno> alunos;
    
    public Turma() {
        
    }
    

    public Turma(Integer id, String descricao, UsuarioProfessor professor, List<UsuarioAluno> alunos) {
        super();
        this.id = id;
        this.descricao = descricao;
        this.professor = professor;
        this.alunos = alunos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioProfessor getProfessor() {
        return professor;
    }

    public void setProfessor(UsuarioProfessor professor) {
        this.professor = professor;
    }

    public List<UsuarioAluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<UsuarioAluno> alunos) {
        this.alunos = alunos;
    }
    
    
}
