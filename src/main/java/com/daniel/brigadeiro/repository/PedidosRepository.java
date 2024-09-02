package com.daniel.brigadeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.daniel.brigadeiro.model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long>{

	 @Query(value = "SELECT * FROM pedidos WHERE status = 'PAGO'", nativeQuery = true)
	 List<Pedidos> findPedidosByStatus();
}
