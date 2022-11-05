package com.ufrn.DTO;

import com.ufrn.enums.RoleUser;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private String login;
    private String senha;
    private RoleUser role;
    
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public RoleUser getRole() {
        return role;
    }
    public void setRole(RoleUser role) {
        this.role = role;
    }  
    
    
}
