package com.ufrn.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaIndividualAllDTO {
    
    private Integer id;
    private LocalDate data;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;
    private Integer aluno;
    private List<EquipamentoReservaDTO> equipamentos;
    
    
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
    public Integer getAluno() {
        return aluno;
    }
    public void setAluno(Integer aluno) {
        this.aluno = aluno;
    }
    public List<EquipamentoReservaDTO> getEquipamentos() {
        return equipamentos;
    }
    public void setEquipamentos(List<EquipamentoReservaDTO> equipamentos) {
        this.equipamentos = equipamentos;
    }
    
    
}
