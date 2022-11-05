package com.ufrn.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ufrn.enums.RoleUser;

public interface Usuario {

    
	public Integer getId();

	public void setId(Integer id);

	public String getLogin();

	public void setLogin(String login);

	public String getSenha();

	public void setSenha(String senha);

	public String getEmail();

	public void setEmail(String email);

    public RoleUser getRole();

    public void setRole(RoleUser role);
	
}
