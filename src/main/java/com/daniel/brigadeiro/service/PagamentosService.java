package com.daniel.brigadeiro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.daniel.brigadeiro.model.Pagamentos;
import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.DTO.PagamentosDTO;
import com.daniel.brigadeiro.repository.PagamentosRepository;
import com.daniel.brigadeiro.repository.PedidosRepository;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class PagamentosService {
	
	@Autowired
	PagamentosRepository pagamentosRepository;
	
	@Autowired
	PedidosService pedidosService;
	
	@Autowired
	PedidosRepository pedidosRepository;


	
	public Pagamentos findById(Long id) {
		Optional<Pagamentos> obj = pagamentosRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}
	
	public Pagamentos create(PagamentosDTO objDTO) {
	 Pagamentos pagamento =  pagamentosRepository.save(newPagamento(objDTO));
	 
	 Pedidos pedido = pagamento.getPedido_fk();
	 
	 if(pedido != null) {
		pedido.setStatus("PAGO");
		pedidosRepository.save(pedido);
	 }
	 return pagamento;
	}
	
	private Pagamentos newPagamento(PagamentosDTO obj) {
		Pedidos ped = pedidosService.findById(obj.getPedido_fk());
		
		Pagamentos pagamento = new Pagamentos();
		if(obj.getId() != null) {
			pagamento.setId(obj.getId());
		}
		pagamento.setPedido_fk(ped);
		pagamento.setTipo_pagamento(obj.getTipo_pagamento());
		pagamento.setValor_pagamento(obj.getValor_pagamento());
		return pagamento;
	}
	
	public Pagamentos update(Long id, @Valid PagamentosDTO objDTO ) {
		objDTO.setId(id);
		Pagamentos oldObj = findById(id);
		oldObj = newPagamento(objDTO);
		return pagamentosRepository.save(oldObj);
	}
}
