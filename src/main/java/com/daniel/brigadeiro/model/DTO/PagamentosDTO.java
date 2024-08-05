package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import com.daniel.brigadeiro.model.Pagamentos;
import com.daniel.brigadeiro.model.Pedidos;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PagamentosDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String tipo_pagamento;
	private Double valor_pagamento;
	private Pedidos pedido_fk;
	private Double valor_total;

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro_pagamento = LocalDate.now();

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

	public PagamentosDTO() {
		super();
	}

	public PagamentosDTO(Pagamentos obj) {
		super();
		this.id = obj.getId();
		this.tipo_pagamento = obj.getTipo_pagamento();
		this.valor_pagamento = obj.getValor_pagamento();
		this.pedido_fk = obj.getPedido_fk();
		this.data_registro_pagamento = obj.getData_registro_pagamento();
		this.valor_total = obj.getPedido_fk().getValor_total();
	}
}
