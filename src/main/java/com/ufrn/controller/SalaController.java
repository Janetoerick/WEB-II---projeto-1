package com.ufrn.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.ufrn.model.Sala;
import com.ufrn.service.SalaService;
import com.ufrn.service.UsuarioService;

@Controller
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    SalaService salaService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping("/pageSalas")
    public String pageSalas(@ModelAttribute("id") String id, Model model) {
        model.addAttribute("salas", salaService.getAllSalas());
        model.addAttribute("id", id);    
        if(usuarioService.findById(Integer.parseInt(id)).getPrioridade() == 0) {
            return "user/salas";
        }
        return "admin/salas";
    }
    
    @RequestMapping("/addSala")
    public String addSala(@RequestParam String nome, @RequestParam String local, @RequestParam String andar, 
            @RequestParam String descricao, @RequestParam String id, Model model){
        try {
            Sala temp = new Sala();
            if(andar.chars().allMatch( Character::isDigit ) && andar != "") {
                temp = new Sala(nome,local,Integer.parseInt(andar),descricao);
            }else {
                temp = new Sala(nome,local,descricao);
            }
            if(nome == "" || local == "" || andar == "" || descricao == "" || !andar.chars().allMatch( Character::isDigit )) {
                model.addAttribute("salas", salaService.getAllSalas());
                model.addAttribute("id", id);    
                model.addAttribute("erro", "erro info sala add");
                model.addAttribute("sala", temp);
            } else {
                salaService.add(temp);
                model.addAttribute("salas", salaService.getAllSalas());
                model.addAttribute("id", id);    
            }
            return "admin/salas";    
        } catch(Exception e) {
            System.out.println(e);
        }
        return "admin/salas";
        
    }
    
    @RequestMapping("/infoSala/{id}")
    public String infoSala(@PathVariable("id") String id, Model model){
        if(id.chars().allMatch( Character::isDigit )) {
            Sala temp = salaService.getById(Integer.parseInt(id));
            model.addAttribute("sala", temp);  
            System.out.println(temp.getNome());
        } else {
            return "admin/salas";
        }
        return "admin/infoSala";
    }


}
