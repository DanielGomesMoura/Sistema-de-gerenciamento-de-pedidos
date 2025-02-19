package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.ItensCompra;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItensCompraDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer quantidade;
	private Double valor_unitario;
	private Long insumo_fk;
	private Long compra_fk;
	private String descricao_insumo;

	public ItensCompraDTO(ItensCompra obj) {
		super();
		this.id = obj.getId();
		this.quantidade = obj.getQuantidade();
		this.valor_unitario = obj.getValor_unitario();
		this.insumo_fk = obj.getInsumo_fk().getId();
		this.compra_fk = obj.getCompra_fk().getId();
		this.descricao_insumo = obj.getInsumo_fk().getDescricao();
	}
}
