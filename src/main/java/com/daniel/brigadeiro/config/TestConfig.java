package com.daniel.brigadeiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.daniel.brigadeiro.service.DBService;

@Configuration
@Profile("teste")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	DBService instanciaDB() {
		this.dbService.InstanciaDB();
		return dbService;
	}

}