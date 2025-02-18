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
	
	public InsumosDTO(Insumos obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
	}
}
