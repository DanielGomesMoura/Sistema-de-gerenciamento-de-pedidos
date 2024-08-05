package com.daniel.brigadeiro.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@Entity
public class Pedidos {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Date data_registro;
	private Double valor_total;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData_registro() {
		return data_registro;
	}

	public void setData_registro(Date data_registro) {
		this.data_registro = data_registro;
	}

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Clientes getCliente_fk() {
		return cliente_fk;
	}

	public void setCliente_fk(Clientes cliente_fk) {
		this.cliente_fk = cliente_fk;
	}

	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="cliente_id")
	private Clientes cliente_fk;
	
	public Pedidos(Long id, Date data_registro, Double valor_total, String status, Clientes cliente_fk) {
		super();
		this.id = id;
		this.data_registro = data_registro;
		this.valor_total = valor_total;
		this.status = status;
		this.cliente_fk = cliente_fk;
	}

	public Pedidos() {
		super();
	}
	
	

}
