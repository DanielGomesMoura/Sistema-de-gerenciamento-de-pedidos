package com.daniel.brigadeiro.model.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import com.daniel.brigadeiro.model.Clientes;
import com.daniel.brigadeiro.model.Pedidos;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PedidosDTO implements Serializable {

private static final long serialVersionUID = 1L;


	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_registro = LocalDate.now();
	private Double valor_total;
	private Clientes cliente_fk;
	private String nomeCliente; 
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getData_registro() {
		return data_registro;
	}
	public void setData_registro(LocalDate data_registro) {
		this.data_registro = data_registro;
	}
	public Double getValor_total() {
		return valor_total;
	}
	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	public Clientes getCliente_fk() {
		return cliente_fk;
	}
	public void setCliente_fk(Clientes cliente_fk) {
		this.cliente_fk = cliente_fk;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String status;

	public PedidosDTO() {
		super();
	}
	public PedidosDTO(Pedidos obj) {
		super();
		this.id = obj.getId();
		this.data_registro = obj.getData_registro();
		this.valor_total = obj.getValor_total();
		this.cliente_fk = obj.getCliente_fk();
		this.status = obj.getStatus();
		this.nomeCliente = obj.getCliente_fk().getNome();
	}
}
