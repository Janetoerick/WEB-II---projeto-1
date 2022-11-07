package com.ufrn.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class HorarioDTO {

    private LocalDate data;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;
    
    
    
    public HorarioDTO(LocalDate data, LocalTime horarioInicial, LocalTime horarioFinal) {
        super();
        this.data = data;
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
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
    
    
    
}
