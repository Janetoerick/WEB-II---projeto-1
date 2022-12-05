package com.ufrn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ufrn.DTO.CreateUsuarioDTO;
import com.ufrn.DTO.CredenciaisDTO;
import com.ufrn.DTO.passwordAttDTO;
import com.ufrn.enums.RoleUser;
import com.ufrn.exception.RegraNegocioException;
import com.ufrn.exception.SenhaInvalidaException;
import com.ufrn.exception.UsuarioNotExistException;
import com.ufrn.model.Usuario;
import com.ufrn.model.UsuarioAdmin;
import com.ufrn.model.UsuarioAluno;
import com.ufrn.model.UsuarioProfessor;
import com.ufrn.repository.UsuarioAdminRepository;
import com.ufrn.repository.UsuarioAlunoRepository;
import com.ufrn.repository.UsuarioProfessorRepository;

@Component
public class UsuarioService implements UserDetailsService {

    private final RoleUser ADMIN = RoleUser.ADMIN;
    private final RoleUser ALUNO = RoleUser.ALUNO; 
    private final RoleUser PROFESSOR = RoleUser.PROFESSOR; 
    
    @Autowired
    private UsuarioAdminRepository repositoryAdmin;
    
    @Autowired
    private UsuarioAlunoRepository repositoryAluno;
    
    @Autowired
    private UsuarioProfessorRepository repositoryProfessor;
    
    @Autowired
    public PasswordEncoder passwordEncoder;
    
    public CreateUsuarioDTO save(CreateUsuarioDTO usuario) {
        
        if(usuario.getSenha() == null || usuario.getLogin() == null 
                || usuario.getEmail() == null) {
            throw new RegraNegocioException("Campos em falta");
        }
        
        for (Usuario u : repositoryAdmin.findAll()) {
            if(u.getLogin().equals(usuario.getLogin())) {
                throw new RegraNegocioException("login indisponivel");
            }
        }
        
        for (Usuario u : repositoryAluno.findAll()) {
            if(u.getLogin().equals(usuario.getLogin())) {
                throw new RegraNegocioException("login indisponivel");
            }
        }
        
        for (Usuario u : repositoryProfessor.findAll()) {
            if(u.getLogin().equals(usuario.getLogin())) {
                throw new RegraNegocioException("login indisponivel");
            }
        }
        
        if(usuario.getRole() == ADMIN) {
            UsuarioAdmin temp = new UsuarioAdmin(usuario.getLogin(), usuario.getSenha(), 
                    usuario.getEmail(), usuario.getRole());
            
            repositoryAdmin.save(temp);
        } else if (usuario.getRole() == ALUNO) {
            UsuarioAluno temp = new UsuarioAluno(usuario.getLogin(), usuario.getSenha(), 
                    usuario.getEmail(), usuario.getRole());
            repositoryAluno.save(temp);
        } else if (usuario.getRole() == PROFESSOR) {
            UsuarioProfessor temp = new UsuarioProfessor(usuario.getLogin(), usuario.getSenha(), 
                    usuario.getEmail(), usuario.getRole());
            repositoryProfessor.save(temp);
        } else {
            throw new RegraNegocioException("Role de usuario invalido!");
        }
        
        return usuario;
    }
    
    public String typeUser(String login) {
        
        for (UsuarioAdmin u : repositoryAdmin.findAll()) {
            if(u.getLogin().equals(login)) {
                return "admin";
            }
        }
        
        for (UsuarioAluno u : repositoryAluno.findAll()) {
            if(u.getLogin().equals(login)) {
                return "aluno";
            }
        }
        
        for (UsuarioProfessor u : repositoryProfessor.findAll()) {
            if(u.getLogin().equals(login)) {
                return "professor";
            }
        }
        
        throw new UsuarioNotExistException();
    }
    
    
    public UserDetails autenticar(CredenciaisDTO usuario) {
        
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches( usuario.getSenha(), user.getPassword() );
        
//        if(usuario.getSenha().equals(user.getPassword())) {
        if(senhasBatem) {
            return user;   
        }
         
        
        throw new SenhaInvalidaException();
    }
    
    public UserDetails loadUserByUsername(String username) {
        
        boolean verify = false;

        for (Usuario user : repositoryAdmin.findAll()) {
            if (user.getLogin().equals(username)) {
                String[] roles = new String[] {"ADMIN"};
                return User
                        .builder()
                        .username(user.getLogin())
                        .password(user.getSenha())
                        .roles(roles)
                        .build();
            }
        }
        
        if(!verify) {
            for (Usuario user : repositoryAluno.findAll()) {
                if (user.getLogin().equals(username)) {
                    String[] roles = new String[] {"ALUNO"};
                    return User
                            .builder()
                            .username(user.getLogin())
                            .password(user.getSenha())
                            .roles(roles)
                            .build();
                }
            }    
        }
        if(!verify) {
            for (Usuario user : repositoryProfessor.findAll()) {
                if (user.getLogin().equals(username)) {
                    String[] roles = new String[] {"PROFESSOR"};
                    return User
                            .builder()
                            .username(user.getLogin())
                            .password(user.getSenha())
                            .roles(roles)
                            .build();
                }
            }    
        }
                

        throw new RegraNegocioException("usuario invalido");
        
    }
    
    public passwordAttDTO attPassword( passwordAttDTO passworddto) {
        
        if(!passworddto.getNova_senha().equals(passworddto.getConfirmarSenha())) {
            throw new RegraNegocioException("Senhas não coincidem");
        }
        
        if(passworddto.getTypeUser().equals("aluno")) {
          UsuarioAluno user = repositoryAluno.findByLogin(passworddto.getLogin())
                  .orElseThrow(() -> new UsuarioNotExistException());
          
          if(user.getSenha().equals(passworddto.getSenha_atual())) {
              user.setSenha(passworddto.getNova_senha());
          } else {
              throw new RegraNegocioException("Senha atual errada!");
          }
          
          repositoryAluno.save(user);
        } else if(passworddto.getTypeUser().equals("professor")) {
            UsuarioProfessor user = repositoryProfessor.findByLogin(passworddto.getLogin())
                    .orElseThrow(() -> new UsuarioNotExistException());
            
            if(user.getSenha().equals(passworddto.getSenha_atual())) {
                user.setSenha(passworddto.getNova_senha());
            } else {
                throw new RegraNegocioException("Senha atual errada!");
            }
            
            repositoryProfessor.save(user);
        } else if(passworddto.getTypeUser().equals("admin")) {
            UsuarioAdmin user = repositoryAdmin.findByLogin(passworddto.getLogin())
                    .orElseThrow(() -> new UsuarioNotExistException());
            
            if(user.getSenha().equals(passworddto.getSenha_atual())) {
                user.setSenha(passworddto.getNova_senha());
            } else {
                throw new RegraNegocioException("Senha atual errada!");
            }
            
            repositoryAdmin.save(user);
        }
        return passworddto;
    }
    
    
}
