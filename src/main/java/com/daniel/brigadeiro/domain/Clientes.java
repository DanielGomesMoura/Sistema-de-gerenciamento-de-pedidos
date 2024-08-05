package com.daniel.brigadeiro.domain;

import org.springframework.beans.factory.annotation.Value;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;

@Entity
public class Clientes{

	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	private String nome;
	
	public Clientes(Integer id, String nome, @Email String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	

	public Clientes() {
		super();
	}



	@Email
    @Column(unique = true)
	private String email;
	

}
