package com.daniel.brigadeiro.model.DTO;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagamentosLoteDTO {
	
	private Long id;
	private Long tipo_recebimento_fk;
    private Double valorTotal;
    private Double valor_pagamento;
    private String tipoRecebimento;
    private List<Long> pedido_fk_lote; // Lista de IDs de pedidos
    
    @JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro_pagamento;
}

