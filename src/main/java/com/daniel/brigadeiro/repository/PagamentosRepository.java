package com.daniel.brigadeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.brigadeiro.model.Pagamentos;

public interface PagamentosRepository  extends JpaRepository<Pagamentos,Long >{
	
}