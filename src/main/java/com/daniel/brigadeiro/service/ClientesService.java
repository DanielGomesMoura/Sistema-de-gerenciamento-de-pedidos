package com.daniel.brigadeiro.service;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Clientes;
import com.daniel.brigadeiro.model.DTO.ClientesDTO;
import com.daniel.brigadeiro.repository.ClientesRepository;
import com.daniel.brigadeiro.service.exception.DataIntegrityViolationException;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClientesService {

	@Autowired
	private ClientesRepository clientesRepository;

	public Clientes findById(Long id) {
		Optional<Clientes> obj = clientesRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<Clientes> findAll() {
		return clientesRepository.findAll();
	}

	public Clientes create(ClientesDTO objDTO) {

		objDTO.setId(null);
		validaEmail(objDTO);

		Clientes obj = new Clientes(objDTO);
		return clientesRepository.save(obj);
	}

	private void validaEmail(ClientesDTO objDTO) {
		Optional<Clientes> obj = clientesRepository.findByEmail(objDTO.getEmail());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado");
		}
	}

	public Clientes update(Long id, @Valid ClientesDTO objDTO ) {
		objDTO.setId(id);
		Clientes oldObj = findById(id);
		validaEmail(objDTO);

		oldObj = new Clientes(objDTO);
		return clientesRepository.save(oldObj);		
	}
	
	public void delete(Long id) {
		Clientes obj = findById(id);
		if(obj.getPedidos().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui histórico de compras e não pode ser deletado");
		}
		clientesRepository.deleteById(id);
	}
}
