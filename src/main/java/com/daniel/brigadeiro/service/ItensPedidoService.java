package com.daniel.brigadeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.daniel.brigadeiro.model.ItensPedido;
import com.daniel.brigadeiro.model.DTO.ItensPedidoDTO;
import com.daniel.brigadeiro.repository.ItensPedidoRepository;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ItensPedidoService {
	
	@Autowired
	private ItensPedidoRepository itensPedidoRepository;

	public ItensPedido findById(Long id) {
		Optional<ItensPedido> obj = itensPedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<ItensPedido> findAll() {
		return itensPedidoRepository.findAll();
	}

	public ItensPedido create(ItensPedidoDTO objDTO) {
		objDTO.setId(null);
		ItensPedido obj = new ItensPedido(objDTO);
		return itensPedidoRepository.save(obj);
	}

	public ItensPedido update(Long id, @Valid ItensPedidoDTO objDTO ) {
		objDTO.setId(id);
		ItensPedido oldObj = findById(id);
		oldObj = new ItensPedido(objDTO);
		return itensPedidoRepository.save(oldObj);		
	}
}
