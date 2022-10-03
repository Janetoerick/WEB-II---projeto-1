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
import com.ufrn.service.EquipamentoService;
import com.ufrn.service.SalaService;
import com.ufrn.service.UsuarioService;

@Controller
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    SalaService salaService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    EquipamentoService equipamentoService;
    
    @RequestMapping("/pageSalas/{id}")
    public String pageSalas(@PathVariable("id") String id, Model model) {
        model.addAttribute("salas", salaService.getAllSalas());
        model.addAttribute("id", id);    
        if(usuarioService.findById(Integer.parseInt(id)).getPrioridade() == 0) {
            return "user/salas";
        }
        return "admin/salasAdmin";
    }
    
    @RequestMapping("/pageSalasAdmin")
    public String pageSalasP(Model model) {
        model.addAttribute("salas", salaService.getAllSalas());    
        return "admin/salasAdmin";
    }
    
    @RequestMapping("/pageReserva/{p}")
    public String pageReserva(@PathVariable("p") String p, Model model) {
        model.addAttribute("salas", salaService.getAllSalas());    
        if(p == "0") {
            return "user/salas";
        }
        return "admin/salasAdmin";
    }
    
    @RequestMapping("/addSala")
    public String addSala(@RequestParam String nome, @RequestParam String local, @RequestParam String andar, 
            @RequestParam String descricao, @RequestParam String id, Model model){
        try {
            Sala temp = new Sala();
            if(andar.chars().allMatch( Character::isDigit ) && andar != "") {
                temp = new Sala(nome,local,Integer.parseInt(andar), descricao);
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
            return "admin/salasAdmin";    
        } catch(Exception e) {
            System.out.println(e);
        }
        return "admin/salasAdmin";
        
    }
    
    @RequestMapping("/infoSalaAdmin/{id}")
    public String infoSalaAdmin(@PathVariable("id") String id, Model model){
        if(id.chars().allMatch( Character::isDigit )) {
            Sala temp = salaService.getById(Integer.parseInt(id));
            if(temp != null) {
                model.addAttribute("sala", temp);  
                model.addAttribute("equipamentos", equipamentoService.getBySala(temp));
                return "admin/infoSala";
            }
        }
        return "admin/salasAdmin";
        
    }
    
    @RequestMapping("/infoSala")
    public String infoSala(@RequestParam String id_sala, @RequestParam String id, Model model){
        if(id_sala.chars().allMatch( Character::isDigit ) && id_sala != null && id_sala != "") {
            Sala temp = salaService.getById(Integer.parseInt(id_sala));
            if(temp != null) {
                model.addAttribute("id", id);
                model.addAttribute("sala", temp);  
                model.addAttribute("equipamentos", equipamentoService.getBySala(temp));
                return "user/infoSala";
            }
        }
        return "main";
        
    }
    
    @RequestMapping("/attSala")
    public String attSala(@RequestParam String nome, @RequestParam String local, @RequestParam String andar, 
            @RequestParam String descricao, @RequestParam String id, Model model){
        try {
            Sala temp = new Sala();
            if(andar.chars().allMatch( Character::isDigit ) && andar != "") {
                temp = new Sala(Integer.parseInt(id),nome,local,Integer.parseInt(andar),descricao);
            }
            
            Sala s_original = salaService.getById(Integer.parseInt(id));
            if(nome == "" || local == "" || andar == "" || descricao == "" || !andar.chars().allMatch( Character::isDigit )) {
                model.addAttribute("sala", temp);  
                model.addAttribute("equipamentos", equipamentoService.getBySala(s_original));
                model.addAttribute("erro", "Dados da sala incompletos!");
            } else {
                salaService.add(temp);
                model.addAttribute("sala", temp);  
                model.addAttribute("equipamentos", equipamentoService.getBySala(temp));   
            }
            return "admin/infoSala";    
        } catch(Exception e) {
            System.out.println(e);
        }
        return "main";
        
    }
    
    @RequestMapping("/apagarSala")
    public String apagarSala(@ModelAttribute("id") String id, Model model){
        salaService.deleteById(Integer.parseInt(id));
        model.addAttribute("salas", salaService.getAllSalas());
        return "admin/salasAdmin";
    }


}
