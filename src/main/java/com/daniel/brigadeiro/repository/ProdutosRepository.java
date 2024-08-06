package com.daniel.brigadeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.daniel.brigadeiro.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

}
