package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import com.daniel.brigadeiro.model.Pagamentos;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentosDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long tipo_recebimento_fk;
	private Double valor_pagamento;
	private Long pedido_fk;
	private Double valor_total;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro_pagamento;


	public PagamentosDTO(Pagamentos obj) {
		super();
		this.id = obj.getId();
		this.tipo_recebimento_fk = obj.getTipo_recebimento_fk().getId();
		this.valor_pagamento = obj.getValor_pagamento();
		this.pedido_fk = obj.getPedido_fk().getId();
		this.data_registro_pagamento = obj.getData_registro_pagamento();
		this.valor_total = obj.getPedido_fk().getValor_total();
	}
}
