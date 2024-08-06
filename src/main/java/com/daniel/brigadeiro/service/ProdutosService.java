package com.daniel.brigadeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Produtos;
import com.daniel.brigadeiro.model.DTO.ProdutosDTO;
import com.daniel.brigadeiro.repository.ProdutosRepository;
import com.daniel.brigadeiro.service.exception.DataIntegrityViolationException;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ProdutosService {
	
	@Autowired
	ProdutosRepository produtosRepository;
	
	public Produtos findById(Long id) {
		Optional<Produtos> obj = produtosRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<Produtos> findAll() {
		return produtosRepository.findAll();
	}

	public Produtos create(ProdutosDTO objDTO) {

		objDTO.setId(null);

		Produtos obj = new Produtos(objDTO);
		return produtosRepository.save(obj);
	}

	public Produtos update(Long id, @Valid ProdutosDTO objDTO ) {
		objDTO.setId(id);
		Produtos oldObj = findById(id);

		oldObj = new Produtos(objDTO);
		return produtosRepository.save(oldObj);		
	}
	
	public void delete(Long id) {
		Produtos obj = findById(id);
		if(obj.getItensPedidos().size() > 0) {
			throw new DataIntegrityViolationException("Produto possui histórico de compras e não pode ser deletado");
		}
		produtosRepository.deleteById(id);
	}

}
