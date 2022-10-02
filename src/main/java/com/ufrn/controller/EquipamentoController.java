package com.ufrn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufrn.model.Equipamento;
import com.ufrn.model.Sala;
import com.ufrn.service.EquipamentoService;
import com.ufrn.service.SalaService;

@Controller
@RequestMapping("/equipamento")
public class EquipamentoController {
    
    @Autowired
    EquipamentoService equipamentoService;
    
    @Autowired
    SalaService salaService;
    
    
    @RequestMapping("/addEquipamento")
    public String addEquipamento(@RequestParam String codigo, @RequestParam String descricao,
            @RequestParam String sala, Model model) {
        
        Sala s = salaService.getById(Integer.parseInt(sala));
        Equipamento temp = new Equipamento(Integer.parseInt(codigo), descricao, s);
        
//        equipamentoService.add(temp);
//        return "main";
        if(equipamentoService.add(temp)) {
            model.addAttribute("equipamentos", equipamentoService.getBySala(s));
            model.addAttribute("sala", salaService.getById(Integer.parseInt(sala)));
            return "admin/infoSala";
        } else {
            model.addAttribute("equipamentos", equipamentoService.getBySala(s));
            model.addAttribute("sala", salaService.getById(Integer.parseInt(sala)));
            model.addAttribute("erro", "Código de equipamento já existente na sala!");
            return "admin/infoSala";
        }
            
    }
}
