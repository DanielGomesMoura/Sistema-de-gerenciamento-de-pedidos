package com.daniel.brigadeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Compras;
import com.daniel.brigadeiro.model.Insumos;
import com.daniel.brigadeiro.model.Compras;
import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.Tipo_Recebimento;
import com.daniel.brigadeiro.model.DTO.ComprasDTO;
import com.daniel.brigadeiro.repository.ComprasRepository;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ComprasService {
	
	@Autowired
	private ComprasRepository comprasRepository;
	
	@Autowired
	private InsumosService insumosService;
	
	public Compras findById(Long id) {
		Optional<Compras> obj = comprasRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<Compras> findAll() {
		return comprasRepository.findAll();
	}

	
	public Compras create(ComprasDTO objDTO) {
		 Compras compras =  comprasRepository.save(newCompras(objDTO)); 
		 return compras; 
		}
	
	private Compras newCompras(ComprasDTO obj) {
		Insumos insumos = insumosService.findById(obj.getInsumos_fk());
		
		Compras compras = new Compras();
		if(obj.getId() != null) {
			compras.setId(obj.getId());
		}
		compras.setInsumos_fk(insumos);
		compras.setData_registro(obj.getData_registro());
		compras.setFornecedor(obj.getFornecedor());
		compras.setCusto_unitario(obj.getCusto_unitario());
		compras.setCusto_total(obj.getCusto_total());
		compras.setQuantidade(obj.getQuantidade());
		compras.setNota_fiscal(obj.getNota_fiscal());
		return compras;
	}
	
	public Compras update(Long id, @Valid ComprasDTO objDTO ) {
		objDTO.setId(id);
		Compras oldObj = findById(id);
		oldObj = newCompras(objDTO);
		return comprasRepository.save(oldObj);
	}
}
