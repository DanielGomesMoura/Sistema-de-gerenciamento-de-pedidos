package com.daniel.brigadeiro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daniel.brigadeiro.model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long>{

	@Query("SELECT p FROM Pedidos p " +
		   "JOIN p.cliente_fk c " +
		       "WHERE p.data_registro BETWEEN :dataInicio AND :dataFim ") // Filtra pela data da semana
		List<Pedidos>findByDay(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFinal);
}
