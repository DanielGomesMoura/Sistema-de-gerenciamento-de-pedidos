package com.daniel.brigadeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.brigadeiro.model.Compras;

public interface ComprasRepository extends JpaRepository<Compras,Long> {

}
