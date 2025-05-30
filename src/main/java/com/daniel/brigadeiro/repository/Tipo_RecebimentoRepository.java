package com.daniel.brigadeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daniel.brigadeiro.model.Conta;
import com.daniel.brigadeiro.model.Tipo_Recebimento;
import com.daniel.brigadeiro.projection.TipoRecebimentoProjection;

import java.util.List;
import java.util.Optional;



public interface Tipo_RecebimentoRepository extends JpaRepository<Tipo_Recebimento, Long>{

	  @Query("SELECT tr FROM Tipo_Recebimento tr WHERE tr.conta_fk = :conta and tr.tipo = :tipo and tr.categoria = :categoria")
	    Optional<Tipo_Recebimento> findByConta(@Param("conta") Conta conta,@Param("tipo") String tipo, @Param("categoria") String categoria);
	    
	    @Query("SELECT tr.id as id, c.conta as conta FROM Tipo_Recebimento tr " +
			       "JOIN tr.conta_fk c where tr.tipo = :tipo and tr.categoria = :categoria")
			List<TipoRecebimentoProjection> findAllTipoAndDescricao(@Param("tipo") String tipo,@Param("categoria") String categoria);
}
