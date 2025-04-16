package com.daniel.brigadeiro.model;

import com.daniel.brigadeiro.model.DTO.InsumosDTO;

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
	
	
	public Insumos(InsumosDTO objDto) {
		super();
		this.id = objDto.getId();
		this.descricao = objDto.getDescricao();
	}
}


