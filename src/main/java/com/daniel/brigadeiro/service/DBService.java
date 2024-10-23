package com.daniel.brigadeiro.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Admin;
import com.daniel.brigadeiro.model.Clientes;
import com.daniel.brigadeiro.model.Conta;
import com.daniel.brigadeiro.model.Tipo_Recebimento;
import com.daniel.brigadeiro.model.DTO.ClientesDTO;
import com.daniel.brigadeiro.model.enums.Perfil;
import com.daniel.brigadeiro.repository.AdminRepository;
import com.daniel.brigadeiro.repository.ClientesRepository;

@Service
public class DBService {

	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	 @Autowired
	    private BCryptPasswordEncoder encoder;
	    
	
	 public void InstanciaDB() {

		 Clientes cli = new Clientes(null,"Daniel Gomes Moura","daniel.tecnicoi@gmail.com",null); 
		 
		 Admin admin = new Admin(null, "daniel.tecnicop@gmail.com", "Daniel Gomes Moura",encoder.encode("Daniel0101"),Perfil.ADMIN);
		
		 
		 clientesRepository.saveAll(Arrays.asList(cli));
		 adminRepository.save(admin);
		 
	    }
}
