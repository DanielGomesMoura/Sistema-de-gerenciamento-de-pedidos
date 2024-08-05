package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.ItensPedido;
import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.Produtos;

public class ItensPedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer quantidade;
	private Double valor_unitario;
	private String unidade;
	private Produtos produto_fk;
	private Pedidos pedido_fk;
	private String descricao_produto; 
	

	public String getDescricao_produto() {
		return descricao_produto;
	}

	public void setDescricao_produto(String descricao_produto) {
		this.descricao_produto = descricao_produto;
	}

	public ItensPedidoDTO(ItensPedido obj) {
		super();
		this.id = obj.getId();
		this.quantidade = obj.getQuantidade();
		this.valor_unitario = obj.getValor_unitario();
		this.unidade = obj.getUnidade();
		this.produto_fk = obj.getProduto_fk();
		this.pedido_fk = obj.getPedido_fk();
		this.descricao_produto = obj.getProduto_fk().getDescricao();		
	}

	public ItensPedidoDTO() {
		super();
	}
	
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

}
