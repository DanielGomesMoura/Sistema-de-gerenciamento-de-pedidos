package com.daniel.brigadeiro.model.DTO;

import java.time.LocalDate;

import com.daniel.brigadeiro.model.Compras;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComprasDTO {
	
	private Long id;
	@NotNull(message = "O campo Cliente é requerido")
	private Long insumos_fk;
	private String descricao;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro;
	private Integer quantidade;
	private Double custo_unitario;
	private Double custo_total;
	private String fornecedor;
	private String nota_fiscal;
	
	
	public ComprasDTO(Compras obj) {
		super();
		this.id = obj.getId();
		this.insumos_fk = obj.getInsumos_fk().getId();
		this.descricao = obj.getInsumos_fk().getDescricao();
		this.data_registro = obj.getData_registro();
		this.quantidade = obj.getQuantidade();
		this.custo_unitario = obj.getCusto_unitario();
		this.custo_total = obj.getCusto_total();
		this.fornecedor = obj.getFornecedor();
		this.nota_fiscal = obj.getNota_fiscal();
	}
}
