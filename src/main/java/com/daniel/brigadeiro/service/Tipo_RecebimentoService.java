package com.daniel.brigadeiro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Conta;
import com.daniel.brigadeiro.model.Pagamentos;
import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.Tipo_Recebimento;
import com.daniel.brigadeiro.model.DTO.PagamentosDTO;
import com.daniel.brigadeiro.model.DTO.Tipo_RecebimentoDTO;
import com.daniel.brigadeiro.repository.Tipo_RecebimentoRepository;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class Tipo_RecebimentoService {
	
	@Autowired
	private Tipo_RecebimentoRepository recebimentoRepository;
	
	@Autowired 
	private ContaService contaService;
	
	public Tipo_Recebimento findById(Long id) {
		Optional<Tipo_Recebimento> obj = recebimentoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<Tipo_Recebimento> findAll() {
		return recebimentoRepository.findAll();
	}
	
	public Tipo_Recebimento create(Tipo_RecebimentoDTO objDTO) {
		Tipo_Recebimento recebimento =  recebimentoRepository.save(newTipo_Recebimento(objDTO));
		 
		 return recebimento;
		}
		
		private Tipo_Recebimento newTipo_Recebimento(Tipo_RecebimentoDTO obj) {
			Conta conta = contaService.findById(obj.getConta_fk());
			
			Tipo_Recebimento recebimento = new Tipo_Recebimento();
			if(obj.getId() != null) {
				recebimento.setId(obj.getId());
			}
			recebimento.setConta_fk(conta);
			recebimento.setTipo(obj.getTipo());
		
			return recebimento;
		}
		
		public Tipo_Recebimento update(Long id, @Valid Tipo_RecebimentoDTO objDTO ) {
			objDTO.setId(id);
			Tipo_Recebimento oldObj = findById(id);
			oldObj = newTipo_Recebimento(objDTO);
			return recebimentoRepository.save(oldObj);
		}
	
	public void delete(Long id) {
		recebimentoRepository.deleteById(id);
	}
}
