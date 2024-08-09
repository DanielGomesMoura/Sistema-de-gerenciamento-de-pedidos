package com.daniel.brigadeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Clientes;
import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.DTO.PedidosDTO;
import com.daniel.brigadeiro.repository.PedidosRepository;
import com.daniel.brigadeiro.service.exception.DataIntegrityViolationException;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class PedidosService {

	@Autowired
	PedidosRepository pedidosRepository;
	
	@Autowired
	ClientesService clientesService;
	
	public Pedidos findById(Long id) {
		Optional<Pedidos> obj = pedidosRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<Pedidos> findAll() {
		return pedidosRepository.findAll();
	}

	public Pedidos create(PedidosDTO objDTO) {
		return pedidosRepository.save(newPedido(objDTO));
	}
	
	private Pedidos newPedido(PedidosDTO obj) {
		Clientes cli = clientesService.findById(obj.getCliente_fk());
		
		Pedidos pedido = new Pedidos();
		if(obj.getId() != null) {
			pedido.setId(obj.getId());
		}
		
		pedido.setCliente_fk(cli);
		pedido.setValor_total(obj.getValor_total());
		pedido.setStatus("ABERTO");
		return pedido;
	
	}

	public Pedidos update(Long id, @Valid PedidosDTO objDTO ) {
		objDTO.setId(id);
		Pedidos oldObj = findById(id);
		oldObj = newPedido(objDTO);
		return pedidosRepository.save(oldObj);
	}
}
