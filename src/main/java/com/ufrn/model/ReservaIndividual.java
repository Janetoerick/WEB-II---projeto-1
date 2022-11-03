package com.ufrn.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reserva_individual")
public class ReservaIndividual extends Reserva{

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "reserva_equipamento",
    joinColumns = @JoinColumn(name = "reserva_id"),
    inverseJoinColumns = @JoinColumn(name = "equipamento_id"))
    private Set<Equipamento> equipamentos;

    public ReservaIndividual(Usuario usuario, LocalDate data, LocalTime horarioInicial, LocalTime horarioFinal) {
        super();
        this.usuario = usuario;
        this.data = data;
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
