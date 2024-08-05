package com.daniel.brigadeiro.model;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@Entity
public class Pagamentos {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro_pagamento = LocalDate.now();
	
	private String tipo_pagamento;
	
	private Double valor_pagamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id")
	private Pedidos pedido_fk;

	public Pagamentos() {
		super();
	}

	public Pagamentos(Long id, LocalDate data_registro_pagamento, String tipo_pagamento, Double valor_pagamento,
			Pedidos pedido_fk) {
		super();
		this.id = id;
		this.data_registro_pagamento = data_registro_pagamento;
		this.tipo_pagamento = tipo_pagamento;
		this.valor_pagamento = valor_pagamento;
		this.pedido_fk = pedido_fk;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData_registro_pagamento() {
		return data_registro_pagamento;
	}

	public void setData_registro_pagamento(LocalDate data_registro_pagamento) {
		this.data_registro_pagamento = data_registro_pagamento;
	}

	public String getTipo_pagamento() {
		return tipo_pagamento;
	}

	public void setTipo_pagamento(String tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}

	public Double getValor_pagamento() {
		return valor_pagamento;
	}

	public void setValor_pagamento(Double valor_pagamento) {
		this.valor_pagamento = valor_pagamento;
	}

	public Pedidos getPedido_fk() {
		return pedido_fk;
	}

	public void setPedido_fk(Pedidos pedido_fk) {
		this.pedido_fk = pedido_fk;
	}

}
