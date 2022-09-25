package com.ufrn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.service.EquipamentoService;

@Controller
@RequestMapping("/equipamento")
public class EquipamentoController {
    
    @Autowired
    EquipamentoService equipamentoService;
}
