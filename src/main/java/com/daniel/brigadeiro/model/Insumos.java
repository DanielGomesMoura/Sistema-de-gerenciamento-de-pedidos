package com.daniel.brigadeiro.model;

import java.time.LocalDate;

import com.daniel.brigadeiro.model.DTO.InsumosDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Insumos {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String  descricao;
	private Double custo_medio;
	private Integer estoque;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_ultima_compra;
	
	public Insumos(InsumosDTO objDto) {
		super();
		this.id = objDto.getId();
		this.descricao = objDto.getDescricao();
		this.custo_medio = objDto.getCusto_medio();
		this.estoque = objDto.getEstoque();
		this.data_ultima_compra = objDto.getData_ultima_compra();
	}
}


