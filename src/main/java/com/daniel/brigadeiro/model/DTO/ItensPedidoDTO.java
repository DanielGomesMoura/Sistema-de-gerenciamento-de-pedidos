package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.ItensPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItensPedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer quantidade;
	private Double valor_unitario;
	private Double valor_venda;
	private Double valor_promocional;
	private String unidade;
	private Long produto_fk;
	private Long pedido_fk;
	private String descricao_produto; 
	
	public ItensPedidoDTO(ItensPedido obj) {
		super();
		this.id = obj.getId();
		this.quantidade = obj.getQuantidade();
		this.valor_unitario = obj.getValor_unitario();
		this.valor_venda = obj.getProduto_fk().getValor_venda();
		this.valor_promocional = obj.getProduto_fk().getValor_promocional();
		this.produto_fk = obj.getProduto_fk().getId();
		this.pedido_fk = obj.getPedido_fk().getId();
		this.descricao_produto = obj.getProduto_fk().getDescricao();
		this.unidade = obj.getProduto_fk().getUnidade();
	}

}
