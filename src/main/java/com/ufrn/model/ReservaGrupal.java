package com.ufrn.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufrn.enums.tipoReserva;

@Entity
@Table(name = "reserva_grupal")
public class ReservaGrupal implements Reserva{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private LocalDate data;
    
    private LocalTime horarioInicial;
    
    private LocalTime horarioFinal;

    @OneToOne
    @JoinColumn(name = "turma_id", referencedColumnName = "id")
    @JsonIgnore
    private Turma turma;
    
    @ManyToOne
    @JoinColumn(name = "sala_id")
    @JsonIgnore
    private Sala sala;
    
    public ReservaGrupal () {
        
    }
    

    public ReservaGrupal(LocalDate data, LocalTime horarioInicial, LocalTime horarioFinal, Turma turma, Sala sala) {
        super();
        this.data = data;
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
        this.turma = turma;
        this.sala = sala;
    }





    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public LocalDate getData() {
        return data;
    }


    public void setData(LocalDate data) {
        this.data = data;
    }


    public LocalTime getHorarioInicial() {
        return horarioInicial;
    }


    public void setHorarioInicial(LocalTime horarioInicial) {
        this.horarioInicial = horarioInicial;
    }


    public LocalTime getHorarioFinal() {
        return horarioFinal;
    }


    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }


    public Sala getSala() {
        return sala;
    }


    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    
}
