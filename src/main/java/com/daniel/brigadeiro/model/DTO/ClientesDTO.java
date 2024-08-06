package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.Clientes;

import jakarta.validation.constraints.NotNull;

public class ClientesDTO implements Serializable{

private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotNull(message = "O campo EMAIL é requerido")
    private String email;
	@NotNull(message = "O campo NOME é requerido")
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
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}
	
	public ClientesDTO() {
		super();
	}
}
