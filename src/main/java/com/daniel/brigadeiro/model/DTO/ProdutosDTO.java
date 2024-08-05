package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.Produtos;

public class ProdutosDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private Double valor_custo;
	private Double valor_venda;
	private String unidade;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor_custo() {
		return valor_custo;
	}
	public void setValor_custo(Double valor_custo) {
		this.valor_custo = valor_custo;
	}
	public Double getValor_venda() {
		return valor_venda;
	}
	public void setValor_venda(Double valor_venda) {
		this.valor_venda = valor_venda;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	public ProdutosDTO() {
		super();
	}
	
	public ProdutosDTO(Produtos obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.valor_custo = obj.getValor_custo();
		this.valor_venda = obj.getValor_venda();
		this.unidade = obj.getUnidade();
	}

}
