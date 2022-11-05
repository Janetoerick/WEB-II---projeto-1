package com.ufrn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufrn.DTO.CreateUsuarioDTO;
import com.ufrn.DTO.UsuarioDTO;
import com.ufrn.enums.RoleUser;
import com.ufrn.exception.RegraNegocioException;
import com.ufrn.exception.SenhaInvalidaException;
import com.ufrn.exception.UserNameNotFoundException;
import com.ufrn.model.Usuario;
import com.ufrn.model.UsuarioAdmin;
import com.ufrn.model.UsuarioAluno;
import com.ufrn.model.UsuarioProfessor;
import com.ufrn.repository.UsuarioAdminRepository;
import com.ufrn.repository.UsuarioAlunoRepository;
import com.ufrn.repository.UsuarioProfessorRepository;

@Service
public class UsuarioService {

    RoleUser ADMIN = RoleUser.ADMIN;
    RoleUser ALUNO = RoleUser.ALUNO; 
    RoleUser PROFESSOR = RoleUser.PROFESSOR; 
    
//    @Autowired
//    public PasswordEncoder passwordEncoder;
    
    @Autowired
    private UsuarioAdminRepository repositoryAdmin;
    
    @Autowired
    private UsuarioAlunoRepository repositoryAluno;
    
    @Autowired
    private UsuarioProfessorRepository repositoryProfessor;
    
    public CreateUsuarioDTO save(CreateUsuarioDTO usuario) {
        
        if(usuario.getSenha() == null || usuario.getLogin() == null 
                || usuario.getEmail() == null) {
            throw new RegraNegocioException("Campos em falta");
        }
        
        for (Usuario u : repositoryAdmin.findAll()) {
            if(u.getLogin() == usuario.getLogin()) {
                throw new RegraNegocioException("login indisponivel");
            }
        }
        
        for (Usuario u : repositoryAluno.findAll()) {
            if(u.getLogin() == usuario.getLogin()) {
                throw new RegraNegocioException("login indisponivel");
            }
        }
        
        for (Usuario u : repositoryProfessor.findAll()) {
            if(u.getLogin() == usuario.getLogin()) {
                throw new RegraNegocioException("login indisponivel");
            }
        }
        
        
        if(usuario.getRole() == ADMIN) {
            UsuarioAdmin temp = new UsuarioAdmin();
            temp.setLogin(usuario.getLogin());
            temp.setSenha(usuario.getSenha());
            temp.setEmail(usuario.getEmail());
            temp.setRole(usuario.getRole());
            repositoryAdmin.save(temp);
        } else if (usuario.getRole() == ALUNO) {
            UsuarioAluno temp = new UsuarioAluno();
            temp.setLogin(usuario.getLogin());
            temp.setSenha(usuario.getSenha());
            temp.setEmail(usuario.getEmail());
            temp.setRole(usuario.getRole());
            repositoryAluno.save(temp);
        } else if (usuario.getRole() == PROFESSOR) {
            UsuarioProfessor temp = new UsuarioProfessor();
            temp.setLogin(usuario.getLogin());
            temp.setSenha(usuario.getSenha());
            temp.setEmail(usuario.getEmail());
            temp.setRole(usuario.getRole());
            repositoryProfessor.save(temp);
        } else {
            throw new RegraNegocioException("Role de usuario invalido!");
        }
        
        return usuario;
    }
    
    public UserDetails autenticar(UsuarioDTO usuario) {
        
        UserDetails user = loadUserByUsername(usuario.getLogin());
        //boolean senhasBatem = passwordEncoder.matches( usuario.getSenha(), user.getPassword() );
        
        boolean senhasBatem = true;
        if(senhasBatem)
            return user;
        
        throw new SenhaInvalidaException();
    }
    
    public UserDetails loadUserByUsername(String username) {
        
        boolean verify = false;

        for (Usuario user : repositoryAdmin.findAll()) {
            if (user.getLogin() == username) {
                return User
                        .builder()
                        .username(user.getLogin())
                        .password(user.getSenha())
                        .roles("ADMIN")
                        .build();
            }
        }
        
        if(!verify) {
            for (Usuario user : repositoryAluno.findAll()) {
                if (user.getLogin() == username) {
                    return User
                            .builder()
                            .username(user.getLogin())
                            .password(user.getSenha())
                            .roles("ALUNO")
                            .build();
                }
            }    
        }
        if(!verify) {
            for (Usuario user : repositoryProfessor.findAll()) {
                if (user.getLogin() == username) {
                    return User
                            .builder()
                            .username(user.getLogin())
                            .password(user.getSenha())
                            .roles("PROFESSOR")
                            .build();
                }
            }    
        }
                

        throw new RegraNegocioException("role usuario invalido");
        
    }
    
    
}
