package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.Insumos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InsumosDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String  descricao;
	private Integer quantidade;
	private String  unidade;
	private Double  custo_unitario;
	
	public InsumosDTO(Insumos obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.quantidade = obj.getQuantidade();
		this.unidade = obj.getUnidade();
		this.custo_unitario = obj.getCusto_unitario();
	}
}
