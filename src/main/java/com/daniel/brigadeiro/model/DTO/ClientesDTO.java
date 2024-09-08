package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;

import com.daniel.brigadeiro.model.Clientes;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientesDTO implements Serializable{

private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotNull(message = "O campo EMAIL é requerido")
    private String email;
	@NotNull(message = "O campo NOME é requerido")
	private String nome;
		
	public ClientesDTO(Clientes obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}
}
