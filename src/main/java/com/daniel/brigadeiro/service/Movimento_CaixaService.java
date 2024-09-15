package com.daniel.brigadeiro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.model.Conta;
import com.daniel.brigadeiro.model.Movimento_Caixa;
import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.Tipo_Recebimento;
import com.daniel.brigadeiro.model.DTO.Movimento_CaixaDTO;
import com.daniel.brigadeiro.repository.Movimento_CaixaRepository;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class Movimento_CaixaService {

	@Autowired
	private Movimento_CaixaRepository caixaRepository;
	
	@Autowired
	private Tipo_RecebimentoService recebimentoService;
	
	public Movimento_Caixa findById(Long id) {
		Optional<Movimento_Caixa> obj = caixaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<Movimento_Caixa> findAll() {
		return caixaRepository.findAll();
	}
	
	public Movimento_Caixa create(Movimento_CaixaDTO objDTO) {
		Movimento_Caixa recebimento =  caixaRepository.save(newMovimento_Caixa(objDTO));
		 
		 return recebimento;
		}
		
		private Movimento_Caixa newMovimento_Caixa(Movimento_CaixaDTO obj) {
			Tipo_Recebimento recebimento = recebimentoService.findById(obj.getRecebimento_fk());
			
			Movimento_Caixa movimento = new Movimento_Caixa();
			if(obj.getId() != null) {
				movimento.setId(obj.getId());
			}
			movimento.setRecebimento_fk(recebimento);
			movimento.setTipo(obj.getTipo());
		
			return movimento;
		}
		
		 public void registrarMovimento(Pedidos pedido, Tipo_Recebimento recebimento) {
		        Movimento_Caixa movimento = new Movimento_Caixa();
		        
		        movimento.setData_registro(LocalDate.now());
		        movimento.setDescricao("Compra - " + LocalDate.now());
		        movimento.setRecebimento_fk(recebimento);
		        movimento.setTipo("ENTRADA");
		        movimento.setValor(pedido.getValor_total());
		        
		        caixaRepository.save(movimento);
		    }
		
		public Movimento_Caixa update(Long id, @Valid Movimento_CaixaDTO objDTO ) {
			objDTO.setId(id);
			Movimento_Caixa oldObj = findById(id);
			oldObj = newMovimento_Caixa(objDTO);
			return caixaRepository.save(oldObj);
		}
	
	public void delete(Long id) {
		caixaRepository.deleteById(id);
	}
}
