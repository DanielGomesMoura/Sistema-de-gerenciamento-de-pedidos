package com.daniel.brigadeiro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@Entity
public class ItensPedido {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;
	
	private Double valor_unitario;
	
	private String unidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id")
	private Produtos produto_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id")
	private Pedidos pedido_fk;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(Double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Produtos getProduto_fk() {
		return produto_fk;
	}

	public void setProduto_fk(Produtos produto_fk) {
		this.produto_fk = produto_fk;
	}

	public Pedidos getPedido_fk() {
		return pedido_fk;
	}

	public void setPedido_fk(Pedidos pedido_fk) {
		this.pedido_fk = pedido_fk;
	}

	public ItensPedido(Long id, Integer quantidade, Double valor_unitario, String unidade, Produtos produto_fk,
			Pedidos pedido_fk) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.valor_unitario = valor_unitario;
		this.unidade = unidade;
		this.produto_fk = produto_fk;
		this.pedido_fk = pedido_fk;
	}

	public ItensPedido() {
		super();
	}
	
}
