package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;
import java.time.LocalDate;

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
	private Double custo_medio;
	private Integer estoque;
	private LocalDate data_ultima_compra;
	
	public InsumosDTO(Insumos obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.custo_medio = obj.getCusto_medio();
		this.estoque = obj.getEstoque();
		this.data_ultima_compra = obj.getData_ultima_compra();
	}
}
