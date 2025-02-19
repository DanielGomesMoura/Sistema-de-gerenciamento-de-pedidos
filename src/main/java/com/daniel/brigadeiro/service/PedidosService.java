package com.daniel.brigadeiro.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.brigadeiro.model.Clientes;
import com.daniel.brigadeiro.model.ItensPedido;
import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.Produtos;
import com.daniel.brigadeiro.model.DTO.ItensPedidoDTO;
import com.daniel.brigadeiro.model.DTO.PedidosDTO;
import com.daniel.brigadeiro.model.DTO.RankDTO;
import com.daniel.brigadeiro.repository.ItensPedidoRepository;
import com.daniel.brigadeiro.repository.PedidosRepository;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;

@Service
public class PedidosService {
	
	private static final Logger LOGGER = Logger.getLogger(PedidosService.class.getName());

	@Autowired
	PedidosRepository pedidosRepository;

	@Autowired
	ItensPedidoRepository itensPedidoRepository;
	
	@Autowired
	ItensPedidoService itensPedidoService;

	@Autowired
	ClientesService clientesService;

	@Autowired
	ProdutosService produtosService;

	public Pedidos findById(Long id) {
		Optional<Pedidos> obj = pedidosRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

//	public List<Pedidos> findAll() {
//		return pedidosRepository.findAll();
//	}
	
	public List<Pedidos> findByDay(LocalDate dataInicial, LocalDate dataFinal, String situacao) {
		if(situacao.equals("TODOS"))
		   situacao = "%";
		return pedidosRepository.findByDay(dataInicial, dataFinal, situacao);
	} 
	
	//CONSULTA USANDO JPQL
	
	public List<RankDTO> ranking() {
		// Obter as datas de início (segunda-feira) e fim (sexta-feira) da semana atual
        LocalDate[] intervaloSemana = DataSemanaService.getIntervaloSemana();
        LocalDate dataInicio = intervaloSemana[0]; // Segunda-feira
        LocalDate dataFim = intervaloSemana[1]; // Sexta-feira
		 List<RankDTO> result = itensPedidoRepository.findPedidosByStatus(dataInicio,dataFim);
		    int index = 1;
		    for (RankDTO dto : result) {
		        dto.setRowId(index++); // Supondo que você tenha um campo `rowNumber` no `RankDTO`
		    }
		return result;
	}
	
	//CONSULTA USANDO SQL NATIVO
	
//	public List<RankDTO> ranking(){
//		List<Object[]> resultado = itensPedidoRepository.findPedidosByStatusNativo();
//		List<RankDTO> rank = new ArrayList<>();
//		
//		   for (Object[] result : resultado) {
//	            String nomeCliente = (String) result[0];
//	            Long quantidade = ((Number) result[1]).longValue();
//	            rank.add(new RankDTO(nomeCliente, quantidade));
//	        }
//
//	        return rank;
//	    }

	public Pedidos create(PedidosDTO objDTO) {
		return pedidosRepository.save(newPedido(objDTO));
	}

	private Pedidos newPedido(PedidosDTO objDTO) {
		Clientes cli = clientesService.findById(objDTO.getCliente_fk());

		Pedidos pedido = new Pedidos();
		if (objDTO.getId() != null) {
			pedido.setId(objDTO.getId());
		}

		pedido.setCliente_fk(cli);
		pedido.setValor_total(objDTO.getValor_total());
		pedido.setStatus("ABERTO");
		pedido.setData_registro(objDTO.getData_registro());

		// Associa os ItensPedido ao Pedido
		if (objDTO.getItensPedido() != null && !objDTO.getItensPedido().isEmpty()) {
			List<ItensPedido> itensPedidoList = new ArrayList<>();
			for (ItensPedidoDTO itemDTO : objDTO.getItensPedido()) {
				Produtos pro = produtosService.findById(itemDTO.getProduto_fk());
				ItensPedido item = new ItensPedido();
				item.setPedido_fk(pedido);
				item.setValor_unitario(itemDTO.getValor_unitario());
				item.setQuantidade(itemDTO.getQuantidade());
				item.setProduto_fk(pro);
				itensPedidoList.add(item);
			}
			pedido.setItensPedido(itensPedidoList);
		}
		return pedido;
	}

	@Transactional
	public Pedidos update(Long id, @Valid PedidosDTO objDTO) {
		objDTO.setId(id);
		Pedidos oldObj = findById(id);
		updatePedido(oldObj, objDTO);
		LOGGER.info("Pedido atualizado com sucesso: " + oldObj.getId());
		return pedidosRepository.save(oldObj);
	}

	private void updatePedido(Pedidos oldPedido, PedidosDTO objDTO) {
		// Atualiza os campos do pedido
		Clientes cli = clientesService.findById(objDTO.getCliente_fk());
		oldPedido.setCliente_fk(cli);
		oldPedido.setValor_total(objDTO.getValor_total());
		oldPedido.setData_registro(objDTO.getData_registro());
		
		// Status é mantido como está ou atualizado conforme sua lógica

		// Atualiza os itens do pedido
		List<ItensPedido> newItensPedidoList = new ArrayList<>();

		for (ItensPedidoDTO itemDTO : objDTO.getItensPedido()) {
			ItensPedido item;
			if (itemDTO.getId() != null) {
				// Busca o item existente para atualizar
				item = itensPedidoService.findById(itemDTO.getId());
			} else {
				// Cria um novo item
				item = new ItensPedido();
				item.setPedido_fk(oldPedido);
			}

			Produtos pro = produtosService.findById(itemDTO.getProduto_fk());
			item.setProduto_fk(pro);
			item.setValor_unitario(itemDTO.getValor_unitario());
			item.setQuantidade(itemDTO.getQuantidade());

			newItensPedidoList.add(item);
		}
		 // Identifica os itens que não estão na nova lista para removê-los
	    List<ItensPedido> itensParaRemover = oldPedido.getItensPedido().stream()
	            .filter(item -> !newItensPedidoList.contains(item))
	            .collect(Collectors.toList());
	    
	 // Logs para depuração
        itensParaRemover.forEach(item -> LOGGER.info("Removendo item: " + item.getId()));

	    // Remove os itens do banco de dados
	    if (!itensParaRemover.isEmpty()) {
	        itensPedidoRepository.deleteAll(itensParaRemover);
            LOGGER.info("Itens removidos do banco de dados: " + itensParaRemover.size());
	    }

		// Atualiza a lista de itens do pedido
		oldPedido.getItensPedido().clear();
		oldPedido.getItensPedido().addAll(newItensPedidoList);
	}

	public void atualizarStatusParaPago(Pedidos pedido) {
		pedido.setStatus("PAGO");
		pedidosRepository.save(pedido);
	}
}
