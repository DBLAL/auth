package com.custom.entity;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name = "role")
public class Role implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5843362785947694783L;
	private int id;
    private String role_name;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
