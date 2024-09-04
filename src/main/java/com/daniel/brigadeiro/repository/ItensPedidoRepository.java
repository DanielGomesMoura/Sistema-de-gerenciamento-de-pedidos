package com.daniel.brigadeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.daniel.brigadeiro.model.ItensPedido;
import com.daniel.brigadeiro.model.DTO.RankDTO;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Long>{

	@Query("SELECT new com.daniel.brigadeiro.model.DTO.RankDTO(c.nome, SUM(i.quantidade) quantidade) " +
		       "FROM ItensPedido i " +
		       "INNER JOIN i.pedido_fk p " +
		       "INNER JOIN p.cliente_fk c " +
		       "WHERE p.status = 'PAGO' " +
		       "GROUP BY c.nome "+
		       "ORDER BY quantidade DESC")
		List<RankDTO> findPedidosByStatus();
	
	
	//CONSULTA COM SQL NATIVO
	
//	  @Query(value = "select c.nome, sum(i.quantidade) as quantidade from pedidos p "
//	  		+ "inner join clientes c on c.id  = p.cliente_id "
//	  		+ "inner join itens_pedido i on i.pedido_id = p.id "
//	  		+ "where p.status = 'PAGO' "
//	  		+ "group by  c.nome "
//	  		+ "order by i.quantidade desc", nativeQuery = true)
//List<Object[]> findPedidosByStatusNativo();
}
