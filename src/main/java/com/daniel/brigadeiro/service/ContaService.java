package com.daniel.brigadeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Clientes;
import com.daniel.brigadeiro.model.Conta;
import com.daniel.brigadeiro.model.DTO.ClientesDTO;
import com.daniel.brigadeiro.model.DTO.ContaDTO;
import com.daniel.brigadeiro.repository.ContaRepository;
import com.daniel.brigadeiro.service.exception.DataIntegrityViolationException;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ContaService {
	
	
	@Autowired
	private ContaRepository contaRepository;

	public Conta findById(Long id) {
		Optional<Conta> obj = contaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<Conta> findAll() {
		return contaRepository.findAll();
	}
	
	public Conta create(ContaDTO objDTO) {

		objDTO.setId(null);
		Conta obj = new Conta(objDTO);
		return contaRepository.save(obj);
	}

	public Conta update(Long id, @Valid ContaDTO objDTO ) {
		objDTO.setId(id);
		Conta oldObj = findById(id);

		oldObj = new Conta(objDTO);
		return contaRepository.save(oldObj);		
	}
	
	public void delete(Long id) {
		contaRepository.deleteById(id);
	}
}
