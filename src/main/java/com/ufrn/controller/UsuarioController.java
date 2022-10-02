package com.ufrn.controller;

import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufrn.model.Usuario;
import com.ufrn.service.SalaService;
import com.ufrn.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    SalaService salaService;
    
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

 
    @RequestMapping(value = "loginUsuario")
    public String loginUsuario(@ModelAttribute("usuario") Usuario usuario, Model model){

        Usuario temp = usuarioService.verifyUser(usuario);
        if(temp != null){
            if(temp.getPrioridade() == 0){
                model.addAttribute("id", temp.getId());
                model.addAttribute("salas", salaService.getAllSalas());
                return "user/salas";
            } else {
                model.addAttribute("id", temp.getId());
                
                model.addAttribute("salas", salaService.getAllSalas());
                return "admin/salasAdmin";
            }
        } else {
            model.addAttribute("erro", "erro login");
            return "main";
        }
    }
}
