package com.custom.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="account")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7489911981457060308L;

	@Id  
	@Column(name="id",nullable = false)  
	@GeneratedValue
	private Long id;

	@Column  
	private String username;  

	@Column  
	private String name;  

	@Column  
	private String password;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns={ @JoinColumn(name="uid",referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="rid",referencedColumnName="id")})
	private List<Role> list;

	
	public List<Role> getList() {
		return list;
	}

	public void setList(List<Role> list) {
		this.list = list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}  



}
