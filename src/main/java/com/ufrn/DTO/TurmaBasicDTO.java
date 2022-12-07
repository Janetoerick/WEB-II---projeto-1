package com.ufrn.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurmaBasicDTO {
    private Integer id;
    private String descricao;
    private String professor;
    private List<String> alunos;
    
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
    public String getProfessor() {
        return professor;
    }
    public void setProfessor(String professor) {
        this.professor = professor;
    }
    public List<String> getAlunos() {
        return alunos;
    }
    public void setAlunos(List<String> alunos) {
        this.alunos = alunos;
    }
    
    
    
}
