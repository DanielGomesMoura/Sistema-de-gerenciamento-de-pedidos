package com.daniel.brigadeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.brigadeiro.model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {

	Optional<Clientes> findByEmail(String email);
}
