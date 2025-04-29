package com.daniel.brigadeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Estoque;
import com.daniel.brigadeiro.model.Insumos;
import com.daniel.brigadeiro.model.DTO.EstoqueDTO;
import com.daniel.brigadeiro.repository.EstoqueRepository;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Autowired 
	private InsumosService insumosService;
	
	public Estoque findById(Long id) {
		Optional<Estoque> obj = estoqueRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}
	

	public List<Estoque> findAll() {
		return estoqueRepository.findAll();
	}

	
	public Estoque create(EstoqueDTO objDTO) {
		 Estoque Estoque =  estoqueRepository.save(newEstoque(objDTO)); 
		 return Estoque; 
		}
	
	private Estoque newEstoque(EstoqueDTO obj) {
		Insumos insumos = insumosService.findById(obj.getInsumo_fk());
		
		Estoque estoque = new Estoque();
		if(obj.getId() != null) {
			estoque.setId(obj.getId());
		}
		estoque.setInsumo(insumos);
		estoque.setQuantidadeAtual(obj.getQuantidadeAtual());
		estoque.setValorUnitario(obj.getValorUnitario());
		estoque.setMotivo(obj.getMotivo());
		estoque.setTipo(obj.getTipo());
		estoque.setData_registro(obj.getData_registro());
		return estoque;
	}
	
	public Estoque update(Long id, @Valid EstoqueDTO objDTO ) {
		objDTO.setId(id);
		Estoque oldObj = findById(id);
		oldObj = newEstoque(objDTO);
		return estoqueRepository.save(oldObj);
	}
}
