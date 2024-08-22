package com.daniel.brigadeiro.model;

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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto_id")
	private Produtos produto_fk;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id")
	private Pedidos pedido_fk;
	
}
