package com.ufrn.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EquipamentoReturnDTO {

    private Integer id;
    private int codigo;
    private String descricao;
    private SalaDTO sala;
    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public SalaDTO getSala() {
        return sala;
    }
    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }
    
    
}
