package com.ufrn.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class UsuarioAdmin extends Usuario{

    public UsuarioAdmin() {
        super();
    }
}
