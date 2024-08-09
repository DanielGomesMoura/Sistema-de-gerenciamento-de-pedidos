package com.daniel.brigadeiro.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Clientes;
import com.daniel.brigadeiro.model.DTO.ClientesDTO;
import com.daniel.brigadeiro.repository.ClientesRepository;

@Service
public class DBService {

	@Autowired
	private ClientesRepository clientesRepository;
	
	
	 public void InstanciaDB() {

		 Clientes cli = new Clientes(null,"Daniel Gomes Moura","daniel.tecnicoi@gmail.com",null); 
		 
		 
		 clientesRepository.saveAll(Arrays.asList(cli));
		 
	    }
}
