package com.daniel.brigadeiro.model;

import java.util.ArrayList;
import java.util.List;

import com.daniel.brigadeiro.model.DTO.ClientesDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;

@Entity
public class Clientes{

	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Email
    @Column(unique = true)
	private String email;
	
	private String nome;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Clientes(Long id, String nome, @Email String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	 public Clientes(ClientesDTO objDTO) {
	        super();
	        this.id = objDTO.getId();
	        this.nome = objDTO.getNome();
	        this.email = objDTO.getEmail();
	    }

	public Clientes() {
		super();
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente_fk")
	private List<Pedidos> pedidos = new ArrayList<>();


	public List<Pedidos> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedidos> pedidos) {
		this.pedidos = pedidos;
	}
}
