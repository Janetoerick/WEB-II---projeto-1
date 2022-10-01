package com.ufrn.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sala")
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;

	private String local;
	
	private int andar;
	
	private String descricao;
	
	@OneToMany(mappedBy = "sala", fetch = FetchType.LAZY)
	private Set<Equipamento> equipamentos;

	public Sala () {
		
	}
	
	public Sala(String nome, String local, int andar, String descricao) {
        super();
        this.nome = nome;
        this.local = local;
        this.andar = andar;
        this.descricao = descricao;
    }

    public Sala(String nome, String local, String descricao) {
        super();
        this.nome = nome;
        this.local = local;
        this.descricao = descricao;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(Set<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}


    public String getLocal() {
        return local;
    }


    public void setLocal(String local) {
        this.local = local;
    }


    public int getAndar() {
        return andar;
    }


    public void setAndar(int andar) {
        this.andar = andar;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
	
	
	
}
