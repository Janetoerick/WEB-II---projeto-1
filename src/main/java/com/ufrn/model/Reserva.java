package com.ufrn.model;

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

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
	@JoinTable(name = "reserva_equipamento",
	joinColumns = @JoinColumn(name = "reserva_id"),
	inverseJoinColumns = @JoinColumn(name = "equipamento_id"))
	private Set<Equipamento> equipamentos;

	
	public Reserva () {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(Set<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	
	
	
}
