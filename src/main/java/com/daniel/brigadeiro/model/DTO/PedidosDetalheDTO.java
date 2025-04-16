package com.daniel.brigadeiro.model.DTO;

import com.daniel.brigadeiro.model.Pedidos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidosDetalheDTO {
	
	    private Long id;
	    private Double valor_total;
	    private String nomeCliente;
	    private String status;

	    public PedidosDetalheDTO(Pedidos obj) {
	        this.id = obj.getId();
	        this.valor_total = obj.getValor_total();
	        this.nomeCliente = obj.getCliente_fk().getNome();
	        this.status = obj.getStatus();
	    }

}
