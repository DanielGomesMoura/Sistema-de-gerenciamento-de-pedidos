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
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produtos {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	private Double valor_custo;
	private Double valor_venda;
	private String unidade;
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto_fk")
	private List<ItensPedido> itensPedido = new ArrayList<>();
	
	public Produtos(ProdutosDTO objDTO) {
		super();
		this.id = objDTO.getId();
		this.descricao = objDTO.getDescricao();
		this.valor_custo = objDTO.getValor_custo();
		this.valor_venda = objDTO.getValor_venda();
		this.unidade = objDTO.getUnidade();
	}

}
