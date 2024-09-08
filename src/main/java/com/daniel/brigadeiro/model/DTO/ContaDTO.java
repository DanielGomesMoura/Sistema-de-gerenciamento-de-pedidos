package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.Conta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String conta;
	
	public ContaDTO(Conta obj) {
		super();
		this.id = obj.getId();
		this.conta = obj.getConta();
	}
}
