package com.ufrn.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ufrn.enums.RoleUser;

@Entity
@Table(name = "professor")
public class UsuarioProfessor implements Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Column(length = 50, nullable = false, unique = true)
    protected String login;

    @Column(length = 50, nullable = false)
    protected String senha;

    protected String email;

    protected RoleUser role;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private Set<Turma> turmas;

    public UsuarioProfessor() {

    }

    public UsuarioProfessor(String login, String email, String senha) {
        this.login = login;
        this.email = email;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(Set<Turma> turmas) {
        this.turmas = turmas;
    }

}
