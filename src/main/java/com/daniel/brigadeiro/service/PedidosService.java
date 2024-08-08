package com.daniel.brigadeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Pedidos findById(Long id) {
		Optional<Pedidos> obj = pedidosRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<Pedidos> findAll() {
		return pedidosRepository.findAll();
	}

	public Pedidos create(PedidosDTO objDTO) {

		objDTO.setId(null);

		Pedidos obj = new Pedidos(objDTO);
		return pedidosRepository.save(obj);
	}

	public Pedidos update(Long id, @Valid PedidosDTO objDTO ) {
		objDTO.setId(id);
		Pedidos oldObj = findById(id);

		oldObj = new Pedidos(objDTO);
		return pedidosRepository.save(oldObj);		
	}
}
