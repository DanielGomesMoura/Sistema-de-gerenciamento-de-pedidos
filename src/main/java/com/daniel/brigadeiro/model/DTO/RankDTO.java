package com.daniel.brigadeiro.model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankDTO {
	private String nome;
	private Long quantidade;
	private Integer rowId;

	public RankDTO(String nome, Long quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}
}
