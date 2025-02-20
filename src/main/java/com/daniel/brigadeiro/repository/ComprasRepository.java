package com.daniel.brigadeiro.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.daniel.brigadeiro.model.Compras;

public interface ComprasRepository extends JpaRepository<Compras,Long> {

	@Query("SELECT c FROM Compras c " +
		   "JOIN c.tipo_recebimento_fk t ") 
			List<Compras>findCompras();
}
