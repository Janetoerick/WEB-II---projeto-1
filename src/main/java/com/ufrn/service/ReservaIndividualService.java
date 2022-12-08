package com.ufrn.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrn.DTO.EquipamentoReservaDTO;
import com.ufrn.DTO.HorarioDTO;
import com.ufrn.DTO.ReservaIndividualAllDTO;
import com.ufrn.DTO.ReservaIndividualDTO;
import com.ufrn.DTO.ReservaIndividualInfoDTO;
import com.ufrn.exception.EquipamentoNotExistException;
import com.ufrn.exception.RegraNegocioException;
import com.ufrn.exception.ReservaInvalidaException;
import com.ufrn.exception.ReservaNotExistException;
import com.ufrn.exception.UserNotExistException;
import com.ufrn.exception.UsuarioNotExistException;
import com.ufrn.model.Equipamento;
import com.ufrn.model.ReservaIndividual;
import com.ufrn.model.UsuarioAluno;
import com.ufrn.repository.EquipamentoRepository;
import com.ufrn.repository.ReservaIndividualRepository;
import com.ufrn.repository.UsuarioAlunoRepository;

@Service
public class ReservaIndividualService {

    @Autowired
    private ReservaIndividualRepository repository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private UsuarioAlunoRepository alunoRepository;

    @Autowired
    private ReservaService service;

    public ReservaIndividual save(ReservaIndividualDTO reserva) {

        List<ReservaIndividual> verify_user = repository.findByUsuario_Login(reserva.getAluno()); 
        if(verify_user.size() > 0)
            throw new RegraNegocioException("Usuario ja tem uma reserva!");
        
        HorarioDTO horario = new HorarioDTO(reserva.getData(), reserva.getHorarioInicial(), reserva.getHorarioFinal());

        Set<Equipamento> eqs = new HashSet<>();

        for (Equipamento equipamento : equipamentoRepository.findAll()) {
            if(equipamento.getSala().getId() == reserva.getSala()) {
                boolean verify = service.verificarDisponibilidade(horario, equipamento.getId()); 
                if (!verify) {
                    throw new ReservaInvalidaException();
                } else {
                    eqs.add(equipamentoRepository.findById(equipamento.getId()).orElseThrow(
                            () -> new EquipamentoNotExistException()));
                    if(eqs.size() == reserva.getQntEquipamentos()) {
                        break;
                    }
                }    
            }            
        }
        if(eqs.size() != reserva.getQntEquipamentos())
            throw new RegraNegocioException("Quantidade de equipamentos não disponíveis na sala");

        ReservaIndividual r = new ReservaIndividual();
        r.setData(reserva.getData());
        r.setHorarioInicial(reserva.getHorarioInicial());
        r.setHorarioFinal(reserva.getHorarioFinal());
        r.setEquipamentos(eqs);
        r.setUsuario(alunoRepository.findByLogin(reserva.getAluno())
                .orElseThrow(() -> new UsuarioNotExistException()));

        repository.save(r);
        return r;
    }

    public List<ReservaIndividual> findByAlunoId(Integer id) {

        alunoRepository.findById(id).orElseThrow(() -> new UserNotExistException());
        
        return repository
                .findByUsuario_Id(id);
    }
    
    public List<ReservaIndividualInfoDTO> findByAlunoLogin(String login) {

        alunoRepository.findByLogin(login).orElseThrow(() -> new UserNotExistException());
        
        List<ReservaIndividualInfoDTO> list_return = new ArrayList<>();
        
        List<ReservaIndividual> reserva = repository.findByUsuario_Login(login);
        
        for (ReservaIndividual ri : reserva) {
            HorarioDTO h = new HorarioDTO(ri.getData(), ri.getHorarioInicial(), ri.getHorarioFinal());
            if(!service.verificarHorarioBoolean(h)) {
                repository.delete(ri);
                break;
            }
            ReservaIndividualInfoDTO reservadto = new ReservaIndividualInfoDTO();
            reservadto.setId(ri.getId());
            reservadto.setData(ri.getData());
            reservadto.setHorarioInicial(ri.getHorarioInicial());
            reservadto.setHorarioFinal(ri.getHorarioFinal());
            List<EquipamentoReservaDTO> list_eq = new ArrayList<>();
            
            for (Equipamento e : ri.getEquipamentos()) {
                if(list_eq.size() < 1) {
                    reservadto.setAndar_sala(e.getSala().getAndar());
                    reservadto.setLocal_sala(e.getSala().getLocal());
                    reservadto.setNome_sala(e.getSala().getNome());                    
                }
                EquipamentoReservaDTO eq = new EquipamentoReservaDTO();
                eq.setId(e.getCodigo());
                list_eq.add(eq);
            }
            reservadto.setEquipamentos(list_eq);
            
            list_return.add(reservadto);
        }
        
        return list_return;
    }
    
    public List<ReservaIndividualAllDTO> findAll() {

        List<ReservaIndividual> list_reserva = repository.findAll();
        List<ReservaIndividualAllDTO> list_return = new ArrayList<>();
        
        for (ReservaIndividual r : list_reserva) {
            ReservaIndividualAllDTO reserva = new ReservaIndividualAllDTO();
            reserva.setId(r.getId());
            reserva.setData(r.getData());
            reserva.setHorarioInicial(r.getHorarioInicial());
            reserva.setHorarioFinal(r.getHorarioFinal());
            reserva.setAluno(r.getUsuario().getId());
            List<EquipamentoReservaDTO> list_eq = new ArrayList<>();
            for (Equipamento e : r.getEquipamentos()) {
                EquipamentoReservaDTO eq = new EquipamentoReservaDTO();
                eq.setId(e.getId());
                list_eq.add(eq);
            }
            reserva.setEquipamentos(list_eq);
            
            list_return.add(reserva);
        }
        
        return list_return;
    }

    public ReservaIndividual update(Integer id, HorarioDTO horario) {

        ReservaIndividual res = repository.findById(id).orElseThrow(() -> new ReservaNotExistException());

        boolean verify = service.verificarDisponibilidade(horario, id); 
        if (!verify) {
            throw new ReservaInvalidaException();
        }
        
        res.setData(horario.getData());
        res.setHorarioInicial(horario.getHorarioInicial());
        res.setHorarioFinal(horario.getHorarioFinal());
        
        repository.save(res);
        return res;
        

    }

    public void deleteById(Integer id) {
        repository
                .findById(id)
                .map(reserva -> {
                    repository.delete(reserva);
                    return 0;
                })
                .orElseThrow(() -> new UserNotExistException());

    }
}
