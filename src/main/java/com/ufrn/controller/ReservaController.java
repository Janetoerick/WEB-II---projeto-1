package com.ufrn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufrn.service.ReservaService;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
    
    @Autowired
    ReservaService reservaService;
}
