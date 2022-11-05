package com.ufrn.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

public interface Reserva {


    public Integer getId();

	public void setId(Integer id);

    public LocalDate getData();

    public void setData(LocalDate data);

    public LocalTime getHorarioInicial();

    public void setHorarioInicial(LocalTime horarioInicial);

    public LocalTime getHorarioFinal();

    public void setHorarioFinal(LocalTime horarioFinal);

	
}
