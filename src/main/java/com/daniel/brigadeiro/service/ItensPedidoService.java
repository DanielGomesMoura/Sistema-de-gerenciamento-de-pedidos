package com.daniel.brigadeiro.service;

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
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ItensPedidoService {
	
	@Autowired
	private ItensPedidoRepository itensPedidoRepository;
	
	@Autowired
	ProdutosService produtosService;

	public ItensPedido findById(Long id) {
		Optional<ItensPedido> obj = itensPedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<ItensPedido> findAll() {
		return itensPedidoRepository.findAll();
	}

	public ItensPedido create(ItensPedidoDTO objDTO) {
		return itensPedidoRepository.save(newPedido(objDTO));
	}
	
	private ItensPedido newPedido(ItensPedidoDTO obj) {
		
		ItensPedido itensPedido = new ItensPedido();
		if(obj.getId() != null) {
			itensPedido.setId(obj.getId());
		}
		
		//itensPedido.setPedido_fk(ped);
		//itensPedido.setProduto_fk(pro);
		itensPedido.setValor_unitario(obj.getValor_unitario());
		itensPedido.setQuantidade(obj.getQuantidade());
		return itensPedido;
	}

	public ItensPedido update(Long id, @Valid ItensPedidoDTO objDTO ) {
		objDTO.setId(id);
		ItensPedido oldObj = findById(id);
		oldObj = newPedido(objDTO);
		return itensPedidoRepository.save(oldObj);
	}
}
