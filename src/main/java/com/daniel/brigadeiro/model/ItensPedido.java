package com.daniel.brigadeiro.model;

import com.daniel.brigadeiro.model.DTO.ItensPedidoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensPedido {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;
	
	private Double valor_unitario;
	
	private String unidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id")
	private Produtos produto_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id")
	private Pedidos pedido_fk;
	
	public ItensPedido(ItensPedidoDTO obj) {
		super();
		this.id = obj.getId();
		this.quantidade = obj.getQuantidade();
		this.valor_unitario = obj.getValor_unitario();
		this.unidade = obj.getUnidade();
		this.produto_fk = obj.getProduto_fk();
		this.pedido_fk = obj.getPedido_fk();	
	}
	
}
