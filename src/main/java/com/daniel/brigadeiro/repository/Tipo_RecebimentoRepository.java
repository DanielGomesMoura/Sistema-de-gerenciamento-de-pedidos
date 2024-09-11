package com.daniel.brigadeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daniel.brigadeiro.model.Conta;
import com.daniel.brigadeiro.model.Tipo_Recebimento;
import java.util.Optional;



public interface Tipo_RecebimentoRepository extends JpaRepository<Tipo_Recebimento, Long>{

	  @Query("SELECT tr FROM Tipo_Recebimento tr WHERE tr.conta_fk = :conta and tr.tipo = :tipo")
	    Optional<Tipo_Recebimento> findByConta(@Param("conta") Conta conta,@Param("tipo")String tipo);
}
