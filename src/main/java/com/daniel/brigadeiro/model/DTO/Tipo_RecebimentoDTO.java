package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.Conta;
import com.daniel.brigadeiro.model.Tipo_Recebimento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Tipo_RecebimentoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long conta_fk;
	private String conta;
	private String tipo;
	
	public Tipo_RecebimentoDTO(Tipo_Recebimento obj) {
		this.id = obj.getId();
		this.conta_fk = obj.getConta_fk().getId();
		this.conta = obj.getConta_fk().getConta();
		this.tipo = obj.getTipo();
	}
}
