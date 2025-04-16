package com.daniel.brigadeiro.model.DTO;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResumoPedidosDTO {
	private double valorTotal;
    private List<PedidosDetalheDTO> pedidos;
    
    
	public ResumoPedidosDTO(double valorTotal, List<PedidosDetalheDTO> pedidos) {
		super();
		this.valorTotal = valorTotal;
		this.pedidos = pedidos;
	}
}
