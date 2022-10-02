package com.ufrn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.service.ReservaService;
import com.ufrn.service.SalaService;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
    
    @Autowired
    ReservaService reservaService;
    
    @Autowired
    SalaService salaService;
    
    @RequestMapping("/pageReservasAdmin")
    public String pageReservasAdmin(Model model) {
        model.addAttribute("salas", salaService.getAllSalas());    
        return "admin/ReservasAdmin";
    }
}
