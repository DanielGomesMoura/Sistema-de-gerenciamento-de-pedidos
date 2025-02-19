package com.daniel.brigadeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.ItensCompra;
import com.daniel.brigadeiro.model.DTO.ItensCompraDTO;
import com.daniel.brigadeiro.repository.ItensCompraRepository;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ItensCompraService {

	@Autowired
	private ItensCompraRepository itensCompraRepository;
	
	
	public ItensCompra findById(Long id) {
		Optional<ItensCompra> obj = itensCompraRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<ItensCompra> findAll() {
		return itensCompraRepository.findAll();
	}

	public ItensCompra create(ItensCompraDTO objDTO) {
		return itensCompraRepository.save(newCompra(objDTO));
	}
	
	private ItensCompra newCompra(ItensCompraDTO obj) {
		
		ItensCompra itensCompra = new ItensCompra();
		if(obj.getId() != null) {
			itensCompra.setId(obj.getId());
		}
		
		//itensPedido.setPedido_fk(ped);
		//itensPedido.setProduto_fk(pro);
		itensCompra.setValor_unitario(obj.getValor_unitario());
		itensCompra.setQuantidade(obj.getQuantidade());
		return itensCompra;
	}

	public ItensCompra update(Long id, @Valid ItensCompraDTO objDTO ) {
		objDTO.setId(id);
		ItensCompra oldObj = findById(id);
		oldObj = newCompra(objDTO);
		return itensCompraRepository.save(oldObj);
	}
}
