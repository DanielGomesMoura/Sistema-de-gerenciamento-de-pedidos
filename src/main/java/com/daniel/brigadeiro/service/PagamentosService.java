package com.daniel.brigadeiro.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.config.webSocket.RankingWebSocketHandler;
import com.daniel.brigadeiro.model.Pagamentos;
import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.Tipo_Recebimento;
import com.daniel.brigadeiro.model.DTO.PagamentosDTO;
import com.daniel.brigadeiro.repository.Movimento_CaixaRepository;
import com.daniel.brigadeiro.repository.PagamentosRepository;
import com.daniel.brigadeiro.repository.PedidosRepository;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class PagamentosService {
	
	@Autowired
	PagamentosRepository pagamentosRepository;

	
	@Autowired
	PedidosRepository pedidosRepository;
	
	@Autowired
	Movimento_CaixaRepository caixaRepository;
	
	@Autowired
	PedidosService pedidosService;
	
	@Autowired
	Movimento_CaixaService caixaService;
	
	@Autowired
	Tipo_RecebimentoService recebimentoService;
	
	@Autowired
    RankingWebSocketHandler webSocketHandler;

	public Pagamentos findById(Long id) {
		Optional<Pagamentos> obj = pagamentosRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}
	
	public Pagamentos create(PagamentosDTO objDTO) {
	 Pagamentos pagamento =  pagamentosRepository.save(newPagamento(objDTO));
	 
	 Pedidos pedido = pagamento.getPedido_fk();
	 
	 if (pedido != null)
	 {
	   pedidosService.atualizarStatusParaPago(pedido); 
	 }
	
	 try {
         caixaService.registrarMovimento(pedido, pagamento.getTipo_recebimento_fk());
     } catch (Exception e) {
         // Tratamento de exceção
         System.err.println("Erro ao registrar movimentação de caixa: " + e.getMessage());
     }
	 
	 // Envia uma atualização para todos os clientes conectados via WebSocket
     try {
         String message = createRankingUpdateMessage(); // Método para criar a mensagem de atualização
         webSocketHandler.sendMessageToClients(message);
     } catch (Exception e) {
         e.printStackTrace();
     }
	 return pagamento;
	}
	
	private String createRankingUpdateMessage() {
        // Implemente a lógica para criar a mensagem de atualização do ranking
        return "Ranking atualizado"; // Exemplo simplificado
    }
	
	private Pagamentos newPagamento(PagamentosDTO obj) {
		Pedidos ped = pedidosService.findById(obj.getPedido_fk());
		Tipo_Recebimento recebimento = recebimentoService.findById(obj.getTipo_recebimento_fk());
		
		Pagamentos pagamento = new Pagamentos();
		if(obj.getId() != null) {
			pagamento.setId(obj.getId());
		}
		pagamento.setPedido_fk(ped);
		pagamento.setData_registro_pagamento(LocalDate.now());
		pagamento.setTipo_recebimento_fk(recebimento);
		pagamento.setValor_pagamento(obj.getValor_pagamento());
		return pagamento;
	}
	
	public Pagamentos update(Long id, @Valid PagamentosDTO objDTO ) {
		objDTO.setId(id);
		Pagamentos oldObj = findById(id);
		oldObj = newPagamento(objDTO);
		return pagamentosRepository.save(oldObj);
	}
}
