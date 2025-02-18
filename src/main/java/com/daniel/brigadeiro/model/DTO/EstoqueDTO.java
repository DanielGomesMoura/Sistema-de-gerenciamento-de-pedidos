package com.daniel.brigadeiro.model.DTO;

import com.daniel.brigadeiro.model.Estoque;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstoqueDTO {

	 private Long id;
	 private Long insumo_fk;
	 private String descricao;
	 private Double quantidadeAtual;
	 private Double valorCustoMedio;
	 private Double valorCustoAnterior;
	 
	 
	public EstoqueDTO(Estoque obj) {
		this.id = obj.getId();
		this.insumo_fk = obj.getInsumo().getId();
		this.descricao = obj.getInsumo().getDescricao();
		this.quantidadeAtual = obj.getQuantidadeAtual();
		this.valorCustoMedio = obj.getValorCustoMedio();
		this.valorCustoAnterior = obj.getValorCustoAnterior();
	}
}
