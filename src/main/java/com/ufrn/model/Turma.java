package com.ufrn.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String descricao;
    
    private Usuario professor;
    
    private List<Usuario> estudantes;
    
    public Turma() {
        
    }
    

    public Turma(Integer id, String descricao, Usuario professor, List<Usuario> estudantes) {
        super();
        this.id = id;
        this.descricao = descricao;
        this.professor = professor;
        this.estudantes = estudantes;
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

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public List<Usuario> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<Usuario> estudantes) {
        this.estudantes = estudantes;
    }
    
    
}
