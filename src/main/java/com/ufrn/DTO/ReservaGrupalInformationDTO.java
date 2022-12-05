package com.ufrn.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ufrn.model.Sala;
import com.ufrn.model.Turma;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservaGrupalInformationDTO {

    private Integer id;
    private LocalDate data;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;
    private Turma turma;
    private Sala sala;
    
    
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
