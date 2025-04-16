package com.daniel.brigadeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daniel.brigadeiro.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

	@Query("SELECT e FROM Estoque e where e.insumo.id = :insumo_id ")
	Optional<Estoque> findByInsumosId(@Param("insumo_id") Long id);

}
