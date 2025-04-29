package com.daniel.brigadeiro.model.DTO;

import java.time.LocalDate;

import com.daniel.brigadeiro.model.Estoque;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDTO {

	 private Long id;
	 private Long insumo_fk;
	 private String descricao;
	 private Integer quantidadeAtual;
	 private Double valorUnitario;
	 private String tipo;
	 private String motivo;
	 @JsonFormat(pattern = "dd/MM/yyyy")
     private LocalDate data_registro;
	 
	 
	public EstoqueDTO(Estoque obj) {
		this.id = obj.getId();
		this.insumo_fk = obj.getInsumo().getId();
		this.descricao = obj.getInsumo().getDescricao();
		this.quantidadeAtual = obj.getQuantidadeAtual();
		this.valorUnitario = obj.getValorUnitario();
		this.tipo = obj.getTipo();
		this.motivo = obj.getMotivo();
		this.data_registro = obj.getData_registro();
	}
}
