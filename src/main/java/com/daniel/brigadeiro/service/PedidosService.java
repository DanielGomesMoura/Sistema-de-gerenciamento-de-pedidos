package com.daniel.brigadeiro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Clientes;
import com.daniel.brigadeiro.model.ItensPedido;
import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.Produtos;
import com.daniel.brigadeiro.model.DTO.ItensPedidoDTO;
import com.daniel.brigadeiro.model.DTO.PedidosDTO;
import com.daniel.brigadeiro.repository.ItensPedidoRepository;
import com.daniel.brigadeiro.repository.PedidosRepository;
import com.daniel.brigadeiro.service.exception.DataIntegrityViolationException;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class PedidosService {

	@Autowired
	PedidosRepository pedidosRepository;
	
	@Autowired
	ItensPedidoRepository itensPedidoRepository;
	
	@Autowired
	ClientesService clientesService;
	
	@Autowired
	ProdutosService produtosService;

	
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
	
	private Pedidos newPedido(PedidosDTO objDTO) {
		Clientes cli = clientesService.findById(objDTO.getCliente_fk());
		
		Pedidos pedido = new Pedidos();
		if(objDTO.getId() != null) {
			pedido.setId(objDTO.getId());
		}
		
		pedido.setCliente_fk(cli);
		pedido.setValor_total(objDTO.getValor_total());
		pedido.setStatus("ABERTO");
		pedido.setData_registro(objDTO.getData_registro());
		
		//Associa os ItensPedido ao Pedido
		if(objDTO.getItensPedido()!= null && !objDTO.getItensPedido().isEmpty()) {
			List<ItensPedido> itensPedidoList = new ArrayList<>();
			for(ItensPedidoDTO itemDTO : objDTO.getItensPedido()) {
				Produtos pro = produtosService.findById(itemDTO.getProduto_fk());
				ItensPedido item = new ItensPedido();
				item.setPedido_fk(pedido);
				item.setValor_unitario(itemDTO.getValor_unitario());
				item.setQuantidade(itemDTO.getQuantidade());
				item.setProduto_fk(pro);
				itensPedidoList.add(item);
			}
			pedido.setItensPedido(itensPedidoList);
		}
		return pedido;
	}

	public Pedidos update(Long id, @Valid PedidosDTO objDTO ) {
		objDTO.setId(id);
		Pedidos oldObj = findById(id);
		oldObj = newPedido(objDTO);
		return pedidosRepository.save(oldObj);
	}
}
