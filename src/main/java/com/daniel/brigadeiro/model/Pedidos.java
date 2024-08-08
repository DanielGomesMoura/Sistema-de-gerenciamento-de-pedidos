package com.daniel.brigadeiro.model;


import java.time.LocalDate;
import java.util.Date;

import com.daniel.brigadeiro.model.DTO.PedidosDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedidos {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro = LocalDate.now();
	private Double valor_total;
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="cliente_id")
	private Clientes cliente_fk;

	public Pedidos(PedidosDTO objDTO) {
		super();
		this.id = objDTO.getId();
		this.data_registro = objDTO.getData_registro();
		this.valor_total = objDTO.getValor_total();
		this.cliente_fk = objDTO.getCliente_fk();
		this.status = objDTO.getStatus();
	}
}
