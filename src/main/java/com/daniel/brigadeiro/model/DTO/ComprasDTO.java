package com.daniel.brigadeiro.model.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.daniel.brigadeiro.model.Compras;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComprasDTO {
	
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro;
	private String fornecedor;
  	private Double valor_total;
  	private String nota_fiscal; 
  	@NotNull(message = "A conta é requerida")
  	private Long tipo_recebimento_fk;
  	private String conta;
  	private String forma_pagamento;
  	private List<ItensCompraDTO> itensCompra;
	
	public ComprasDTO(Compras obj) {
		super();
		this.id = obj.getId();
		this.data_registro = obj.getData_registro();
		this.valor_total = obj.getValor_total();
		this.fornecedor = obj.getFornecedor();
		this.nota_fiscal = obj.getNota_fiscal();
		this.tipo_recebimento_fk = obj.getTipo_recebimento_fk().getId();
		this.conta = obj.getTipo_recebimento_fk().getConta_fk().getConta();
		this.forma_pagamento = obj.getTipo_recebimento_fk().getTipo();
		this.itensCompra = obj.getItensCompra().stream()
				.map(ItensCompraDTO::new)
				.collect(Collectors.toList());
	}
}
