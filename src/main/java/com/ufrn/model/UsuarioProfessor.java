package com.ufrn.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "professor")
public class UsuarioProfessor extends Usuario{

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private Set<Turma> turmas;

    
    public Set<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(Set<Turma> turmas) {
        this.turmas = turmas;
    }
    
    
}
