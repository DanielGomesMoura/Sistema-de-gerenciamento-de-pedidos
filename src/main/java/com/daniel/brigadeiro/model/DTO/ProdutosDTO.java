package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.Produtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutosDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private Double valor_custo;
	private Double valor_venda;
	private String unidade;
	
	public ProdutosDTO(Produtos obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.valor_custo = obj.getValor_custo();
		this.valor_venda = obj.getValor_venda();
		this.unidade = obj.getUnidade();
	}
}
