package com.daniel.brigadeiro.model;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.daniel.brigadeiro.model.DTO.PedidosDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedidos {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro;
	private Double valor_total;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="cliente_id")
	private Clientes cliente_fk;
	private String status;
	
    @OneToMany(mappedBy = "pedido_fk",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ItensPedido> itensPedido;
}
