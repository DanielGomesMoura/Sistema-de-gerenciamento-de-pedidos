package com.daniel.brigadeiro.model;

import com.daniel.brigadeiro.model.DTO.ContaDTO;

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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Conta {
	
	@Id
	@EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String conta;
	
	public Conta(ContaDTO objDTO) {
		this.id = objDTO.getId();
		this.conta = objDTO.getConta();
	}

}
