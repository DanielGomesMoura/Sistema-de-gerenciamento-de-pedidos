package com.daniel.brigadeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Conta;
import com.daniel.brigadeiro.model.Tipo_Recebimento;
import com.daniel.brigadeiro.model.DTO.Tipo_RecebimentoDTO;
import com.daniel.brigadeiro.projection.TipoRecebimentoProjection;
import com.daniel.brigadeiro.repository.ContaRepository;
import com.daniel.brigadeiro.repository.Tipo_RecebimentoRepository;
import com.daniel.brigadeiro.service.exception.DataIntegrityViolationException;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class Tipo_RecebimentoService {
	
	@Autowired
	private Tipo_RecebimentoRepository recebimentoRepository;
	
	@Autowired 
	private ContaService contaService;
	
	@Autowired
	private ContaRepository contaRepository;
	
	public Tipo_Recebimento findById(Long id) {
		Optional<Tipo_Recebimento> obj = recebimentoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<Tipo_Recebimento> findAll() {
		return recebimentoRepository.findAll();
	}
	
	public List<TipoRecebimentoProjection> findByTipoRecebimento(String tipo,String categoria) {
		return recebimentoRepository.findAllTipoAndDescricao(tipo, categoria);
	}
	
	private void validarConta(Tipo_RecebimentoDTO objDTO) {
	    // Primeiro, busque a conta pelo ID
	    Conta conta = contaRepository.findById(objDTO.getConta_fk())
	        .orElseThrow(() -> new DataIntegrityViolationException("Conta não encontrada"));

	    // Agora, verifique se já existe um Tipo_Recebimento com essa conta
	    Optional<Tipo_Recebimento> obj = recebimentoRepository.findByConta(conta,objDTO.getTipo(),objDTO.getCategoria());

	    // Verifica se já existe um Tipo_Recebimento com a mesma conta e se o ID é diferente (caso seja uma atualização)
	    if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
	        throw new DataIntegrityViolationException("Conta já cadastrada");
	    }
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
			validarConta(obj);
			recebimento.setConta_fk(conta);
			recebimento.setTipo(obj.getTipo());
			recebimento.setCategoria(obj.getCategoria());
		
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
