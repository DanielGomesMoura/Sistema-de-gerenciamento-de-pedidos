package com.daniel.brigadeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.brigadeiro.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

}
