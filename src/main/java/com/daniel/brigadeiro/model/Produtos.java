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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	private Double valor_custo;
	private Double valor_venda;
	private Double valor_promocional;
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
		this.valor_promocional = objDTO.getValor_promocional();
		this.unidade = objDTO.getUnidade();
	}
}
