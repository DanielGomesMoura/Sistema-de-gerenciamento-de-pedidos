package com.daniel.brigadeiro.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Compras {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro;
	private String fornecedor;
  	private Double valor_total;
  	private String nota_fiscal;
  	
  	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = 	"recebimento_id")
	private Tipo_Recebimento tipo_recebimento_fk;    
  	
  	@OneToMany(mappedBy = "compra_fk",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ItensCompra> itensCompra;

}
