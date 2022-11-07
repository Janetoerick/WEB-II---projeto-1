package com.ufrn.DTO;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TurmaDTO {

    private String descricao;
    private UserDTO professor;
    private List<UserDTO> alunos;
    
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public UserDTO getProfessor() {
        return professor;
    }
    public void setProfessor(UserDTO professor) {
        this.professor = professor;
    }
    public List<UserDTO> getAlunos() {
        return alunos;
    }
    public void setAlunos(List<UserDTO> alunos) {
        this.alunos = alunos;
    }
    
    
    
    
}
