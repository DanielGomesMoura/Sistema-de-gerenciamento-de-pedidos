package com.daniel.brigadeiro.model;

import java.util.ArrayList;
import java.util.List;

import com.daniel.brigadeiro.model.DTO.ProdutosDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;

@Entity
public class Produtos {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	private Double valor_custo;
	private Double valor_venda;
	private String unidade;
	
	
	public Produtos() {
		super();
	}
	
	public Produtos(Long id, String descricao, Double valor_custo, Double valor_venda, String unidade) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor_custo = valor_custo;
		this.valor_venda = valor_venda;
		this.unidade = unidade;
	}
	
	public Produtos(ProdutosDTO objDTO) {
		super();
		this.id = objDTO.getId();
		this.descricao = objDTO.getDescricao();
		this.valor_custo = objDTO.getValor_custo();
		this.valor_venda = objDTO.getValor_venda();
		this.unidade = objDTO.getUnidade();
	}
	
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto_fk")
	private List<ItensPedido> itensPedido = new ArrayList<>();


	public List<ItensPedido> getItensPedidos() {
		return itensPedido;
	}


	public void setItensPedidos(List<ItensPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

}
