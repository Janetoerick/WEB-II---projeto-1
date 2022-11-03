package com.ufrn.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "aluno")
public class UsuarioAluno extends Usuario{

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<ReservaIndividual> reservas;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "aluno_turma",
    joinColumns = @JoinColumn(name = "aluno_id"),
    inverseJoinColumns = @JoinColumn(name = "turma_id"))
    private Set<Turma> turma;
    
    
    
    public Set<ReservaIndividual> getReservas() {
        return reservas;
    }

    public void setReservas(Set<ReservaIndividual> reservas) {
        this.reservas = reservas;
    }

    public Set<Turma> getTurma() {
        return turma;
    }

    public void setTurma(Set<Turma> turma) {
        this.turma = turma;
    }
    
    
}
