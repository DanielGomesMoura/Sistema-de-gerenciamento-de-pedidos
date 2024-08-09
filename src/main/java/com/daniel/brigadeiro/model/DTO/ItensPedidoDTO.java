package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.ItensPedido;
import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.Produtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensPedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer quantidade;
	private Double valor_unitario;
	private String unidade;
	private Produtos produto_fk;
	private Pedidos pedido_fk;
	private String descricao_produto; 

	public ItensPedidoDTO(ItensPedido obj) {
		super();
		this.id = obj.getId();
		this.quantidade = obj.getQuantidade();
		this.valor_unitario = obj.getValor_unitario();
		this.unidade = obj.getUnidade();
		this.produto_fk = obj.getProduto_fk();
		this.pedido_fk = obj.getPedido_fk();
		this.descricao_produto = obj.getProduto_fk().getDescricao();		
	}

}
