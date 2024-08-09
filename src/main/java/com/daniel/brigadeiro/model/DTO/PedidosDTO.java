package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import com.daniel.brigadeiro.model.Clientes;
import com.daniel.brigadeiro.model.Pedidos;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidosDTO implements Serializable {

private static final long serialVersionUID = 1L;

	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro = LocalDate.now();
	private Double valor_total;
	@NotNull(message = "O campo Cliente é requerido")
	private Long cliente_fk;
	private String nomeCliente; 
	private String status;
		
	public PedidosDTO(Pedidos obj) {
		super();
		this.id = obj.getId();
		this.valor_total = obj.getValor_total();
		this.cliente_fk = obj.getCliente_fk().getId();
		this.status = obj.getStatus();
		this.nomeCliente = obj.getCliente_fk().getNome();
	}
}
