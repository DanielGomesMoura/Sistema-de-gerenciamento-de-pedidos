package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.Clientes;

public class ClientesDTO implements Serializable{

private static final long serialVersionUID = 1L;
	
	private Long id;
    private String email;
	private String nome;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ClientesDTO(Clientes obj) {
		super();
		this.id = obj.getId();
		this.email = obj.getEmail();
		this.nome = obj.getNome();
	}
	
	public ClientesDTO() {
		super();
	}
}
