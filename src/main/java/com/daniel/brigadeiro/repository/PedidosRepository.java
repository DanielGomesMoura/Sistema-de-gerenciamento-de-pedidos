package com.daniel.brigadeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.daniel.brigadeiro.model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long>{

}
