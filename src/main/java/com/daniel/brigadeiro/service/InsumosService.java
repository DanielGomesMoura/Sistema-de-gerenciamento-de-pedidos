package com.daniel.brigadeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Insumos;
import com.daniel.brigadeiro.model.DTO.InsumosDTO;
import com.daniel.brigadeiro.repository.InsumosRepository;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class InsumosService {

	@Autowired
	InsumosRepository insumosRepository;
	
	public Insumos findById(Long id) {
		Optional<Insumos> obj = insumosRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<Insumos> findAll() {
		return insumosRepository.findAll();
	}

	public Insumos create(InsumosDTO objDTO) {

		objDTO.setId(null);

		Insumos obj = new Insumos(objDTO);
		return insumosRepository.save(obj);
	}

	public Insumos update(Long id, @Valid InsumosDTO objDTO ) {
		objDTO.setId(id);
		Insumos oldObj = findById(id);

		oldObj = new Insumos(objDTO);
		return insumosRepository.save(oldObj);		
	}
	
	public void delete(Long id) {
		insumosRepository.deleteById(id);
	}
}

