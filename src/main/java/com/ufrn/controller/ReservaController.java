package com.ufrn.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufrn.model.Reserva;
import com.ufrn.model.Sala;
import com.ufrn.service.EquipamentoService;
import com.ufrn.service.ReservaService;
import com.ufrn.service.SalaService;
import com.ufrn.service.UsuarioService;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
    
    @Autowired
    ReservaService reservaService;
    
    @Autowired
    SalaService salaService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    EquipamentoService equipamentoService;
    
    @RequestMapping("/pageReservasAdmin")
    public String pageReservasAdmin(Model model) {
        model.addAttribute("reservas", reservaService.getAllReservas());    
        return "admin/ReservasAdmin";
    }
    
    @RequestMapping("/pageReservasUser/{id}")
    public String pageReservasUser(@PathVariable("id") String id, Model model) {
        if(id.chars().allMatch( Character::isDigit ) && id != "") {
            model.addAttribute("id", id);
            model.addAttribute("reservas", reservaService.getAllReservasByIdUser(Integer.parseInt(id)));    
            return "user/ReservasUser";
        }
        return "main";
    }
    
    @RequestMapping("/addReserva")
    public String addReserva(@RequestParam String data, @RequestParam String horaInicio,
            @RequestParam String horaFinal, @RequestParam String qntEquipamentos,
            @RequestParam String id_sala, @RequestParam String id_usuario, Model model) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        Reserva reserva = new Reserva(usuarioService.findById(Integer.parseInt(id_usuario)),
                LocalDate.parse(data, formatter), LocalTime.parse(horaInicio),
                LocalTime.parse(horaFinal));
        
        int qntEq = Integer.parseInt(qntEquipamentos);
        if(qntEq > 0 && qntEq < 3 && reservaService.add(reserva, Integer.parseInt(id_sala),qntEq)) {
            model.addAttribute("id", id_usuario);
            model.addAttribute("reservas", reservaService.getAllReservasByIdUser(Integer.parseInt(id_usuario)));    
            return "user/reservasUser";
            //return "main";
        } else {
            Sala s = salaService.getById(Integer.parseInt(id_sala));
            model.addAttribute("id", id_usuario);
            model.addAttribute("sala", s);  
            model.addAttribute("equipamentos", equipamentoService.getBySala(s));
            if(qntEq > 0 && qntEq < 3) {
                model.addAttribute("erro", "Não existem equipamentos na sala livres no momento solicitado!");
            } else {
                model.addAttribute("erro", "Dados Incoerentes!");
            }
            
            return "user/infoSala";
        }
        
    }

    
    @RequestMapping("/apagarReserva")
    public String apagarSala(@RequestParam String id_usuario, @RequestParam String id_reserva, Model model){
        
        reservaService.deleteById(Integer.parseInt(id_reserva));
        
        model.addAttribute("id", id_usuario);
        model.addAttribute("reservas", reservaService.getAllReservasByIdUser(Integer.parseInt(id_usuario)));
        return "user/reservasUser";
    }
    
}
