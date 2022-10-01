package com.ufrn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.model.Usuario;
import com.ufrn.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    public boolean add(Usuario usuario){
        if(usuarioRepository.save(usuario) != null){
            return true;
        };
        return false;
    } 

    public Usuario verifyUser(Usuario usuario){
        return usuarioRepository.findByLogin(usuario.getLogin());
    }
    
    public Usuario findById(int id) {
        return usuarioRepository.findById(id).map(usuario -> {
            return usuario;
        }).orElseThrow(() -> null);
    }
}
