package com.ufrn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.DTO.passwordAttDTO;
import com.ufrn.service.UsuarioService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/configUser")
@RequiredArgsConstructor
public class UsuarioConfigController {

    @Autowired
    private UsuarioService service;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @PutMapping("/password")
    public passwordAttDTO attSenha(@RequestBody passwordAttDTO passworddto) {
        System.out.println("entrou!!!");
//        passworddto.setSenha_atual(passwordEncoder.encode(passworddto.getSenha_atual()));
//        passworddto.setNova_senha(passwordEncoder.encode(passworddto.getNova_senha()));
//        passworddto.setConfirmarSenha(passwordEncoder.encode(passworddto.getConfirmarSenha()));
        
        return service.attPassword(passworddto);
    }
    
}
