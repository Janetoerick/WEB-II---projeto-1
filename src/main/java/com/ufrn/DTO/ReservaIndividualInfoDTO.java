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
public class ReservaIndividualInfoDTO {
    
    private Integer id;
    private LocalDate data;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;
    private String nome_sala;
    private int andar_sala;
    private String local_sala;
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
    public String getNome_sala() {
        return nome_sala;
    }
    public void setNome_sala(String nome_sala) {
        this.nome_sala = nome_sala;
    }
    public int getAndar_sala() {
        return andar_sala;
    }
    public void setAndar_sala(int andar_sala) {
        this.andar_sala = andar_sala;
    }
    public String getLocal_sala() {
        return local_sala;
    }
    public void setLocal_sala(String local_sala) {
        this.local_sala = local_sala;
    }
    public List<EquipamentoReservaDTO> getEquipamentos() {
        return equipamentos;
    }
    public void setEquipamentos(List<EquipamentoReservaDTO> equipamentos) {
        this.equipamentos = equipamentos;
    }
    
    
}
