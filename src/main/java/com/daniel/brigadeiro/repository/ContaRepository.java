package com.daniel.brigadeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.brigadeiro.model.Conta;

public interface ContaRepository extends JpaRepository<Conta,Long>{

}
