package com.ufrn.controller;

import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufrn.model.Usuario;
import com.ufrn.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping("/formUsuario")
    public String formUsuario(){
        return "cadastroUsuario";
    }

    @RequestMapping(value = "addUsuario", method = RequestMethod.POST)
    public String addUsuario(@RequestParam String login, @RequestParam String email, @RequestParam String senha, 
    @RequestParam String confsenha, Model model){
        if(login.equals("") || email.equals("") || senha.equals("")){
            model.addAttribute("login", login);
            model.addAttribute("email", email);
            model.addAttribute("erro", "campo erro");
            return "cadastroUsuario";
        } else if(!senha.equals(confsenha)){
            System.out.println(senha + " | " + confsenha);
            model.addAttribute("login", login);
            model.addAttribute("email", email);
            model.addAttribute("erro", "senha erro");
            return "cadastroUsuario";
        } else {
            Usuario usuario = new Usuario(login, email, senha);
            if(usuarioService.add(usuario)){
                return "main";
            } else {
                model.addAttribute("email", email);
                model.addAttribute("erro", "login erro");
                return "cadastroUsuario";
            }
        }
    }

}
