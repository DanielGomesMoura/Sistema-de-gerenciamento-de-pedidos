package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import com.daniel.brigadeiro.model.Movimento_Caixa;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Movimento_CaixaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private Long recebimento_fk;
	private String conta;
	private String tipo_recebimento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro;
	private String tipo;
	private Double valor;
	
	public Movimento_CaixaDTO(Movimento_Caixa obj) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.recebimento_fk = obj.getRecebimento_fk().getId();
		this.conta = obj.getRecebimento_fk().getConta_fk().getConta();
		this.tipo_recebimento = obj.getRecebimento_fk().getTipo();
		this.data_registro = obj.getData_registro();
		this.tipo = obj.getTipo();
		this.valor = obj.getValor();
	}
}
