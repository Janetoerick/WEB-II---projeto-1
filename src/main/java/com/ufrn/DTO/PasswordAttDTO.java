package com.ufrn.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordAttDTO {
    private String login;
    private String senha_atual;
    private String nova_senha;
    private String confirmar_senha;
    private String typeUser;
    
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha_atual() {
        return senha_atual;
    }
    public void setSenha_atual(String senha_atual) {
        this.senha_atual = senha_atual;
    }
    public String getNova_senha() {
        return nova_senha;
    }
    public void setNova_senha(String nova_senha) {
        this.nova_senha = nova_senha;
    }
    public String getConfirmarSenha() {
        return confirmar_senha;
    }
    public void setConfirmarSenha(String confirmar_senha) {
        this.confirmar_senha = confirmar_senha;
    }
    public String getTypeUser() {
        return typeUser;
    }
    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
    
    
    
}
