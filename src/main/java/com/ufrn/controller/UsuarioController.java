package com.ufrn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ufrn.DTO.CreateUsuarioDTO;
import com.ufrn.DTO.CredenciaisDTO;
import com.ufrn.DTO.PasswordAttDTO;
import com.ufrn.DTO.TokenDTO;
import com.ufrn.DTO.UserDTO;
import com.ufrn.exception.SenhaInvalidaException;
import com.ufrn.secutiry.JwtService;
import com.ufrn.service.UsuarioService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUsuarioDTO save( @RequestBody CreateUsuarioDTO usuario ){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return service.save(usuario);
    }
    
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            
            UserDetails usuarioAutenticado = service.autenticar(credenciais);
     
            String token = jwtService.gerarToken(credenciais);
            
            TokenDTO return_token = new TokenDTO();
            return_token.setLogin(credenciais.getLogin());
            return_token.setToken(token);
            return_token.setTypeUser(service.typeUser(credenciais.getLogin()));
            return return_token;
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    
    @PutMapping("/password")
    public PasswordAttDTO attSenha(@RequestBody PasswordAttDTO passworddto) {
        passworddto.setSenha_atual(passwordEncoder.encode(passworddto.getSenha_atual()));
        passworddto.setNova_senha(passwordEncoder.encode(passworddto.getNova_senha()));
        passworddto.setConfirmarSenha(passwordEncoder.encode(passworddto.getConfirmarSenha()));
        
        return service.attPassword(passworddto);
    }
    
    @GetMapping("{type}")
    public List<UserDTO> findUsuario(@PathVariable String type) {
    	return service.findAll(type);
    }
    
}
