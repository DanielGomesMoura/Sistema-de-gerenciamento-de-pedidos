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
import com.daniel.brigadeiro.model.Compras;
import com.daniel.brigadeiro.model.Estoque;
import com.daniel.brigadeiro.model.Insumos;
import com.daniel.brigadeiro.model.ItensCompra;
import com.daniel.brigadeiro.model.ItensPedido;
import com.daniel.brigadeiro.model.Compras;
import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.Produtos;
import com.daniel.brigadeiro.model.Tipo_Recebimento;
import com.daniel.brigadeiro.model.DTO.ComprasDTO;
import com.daniel.brigadeiro.model.DTO.ItensCompraDTO;
import com.daniel.brigadeiro.model.DTO.ItensPedidoDTO;
import com.daniel.brigadeiro.model.DTO.PedidosDTO;
import com.daniel.brigadeiro.repository.ComprasRepository;
import com.daniel.brigadeiro.repository.EstoqueRepository;
import com.daniel.brigadeiro.repository.ItensCompraRepository;
import com.daniel.brigadeiro.service.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ComprasService {
	
	private static final Logger LOGGER = Logger.getLogger(ComprasService.class.getName());

	
	@Autowired
	private ComprasRepository comprasRepository;
	
	@Autowired
	private  ItensCompraService itensCompraService;
	
	@Autowired
	private ItensCompraRepository itensCompraRepository;
	
	@Autowired
	private Tipo_RecebimentoService recebimentoService;
	
	@Autowired
	private InsumosService insumosService;
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	public Compras findById(Long id) {
		Optional<Compras> obj = comprasRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
	}

	public List<Compras> findAll() {
		return comprasRepository.findCompras();
	}

	
	public Compras create(ComprasDTO objDTO) {
		 return comprasRepository.save(newCompras(objDTO));  
		}
	
	private Compras newCompras(ComprasDTO objDTO) {
		Tipo_Recebimento recebimento = recebimentoService.findById(objDTO.getTipo_recebimento_fk());
		
		Compras compra = new Compras();
		if(objDTO.getId() != null) {
			compra.setId(objDTO.getId());
		}
		compra.setTipo_recebimento_fk(recebimento);
		compra.setFornecedor(objDTO.getFornecedor());;
		compra.setData_registro(objDTO.getData_registro());
		compra.setNota_fiscal(objDTO.getNota_fiscal());
		compra.setValor_total(objDTO.getValor_total());
		
		// Associa os ItensCompras a Compra
		if (objDTO.getItensCompra() != null && !objDTO.getItensCompra().isEmpty()) {
			List<ItensCompra> itensCompraList = new ArrayList<>();
			for (ItensCompraDTO itemDTO : objDTO.getItensCompra()) {
				Insumos insumo = insumosService.findById(itemDTO.getInsumo_fk());
				ItensCompra item = new ItensCompra();
				item.setCompra_fk(compra);
				item.setValor_unitario(itemDTO.getValor_unitario());
				item.setQuantidade(itemDTO.getQuantidade());
				item.setInsumo_fk(insumo);
				itensCompraList.add(item);
				
				 // Atualiza o estoque do insumo
				Estoque estoque = new Estoque();
		        Integer estoqueAtual = estoque.getQuantidadeAtual(); // Supondo que você tenha esse campo
		        Double custo = estoque.getValorCustoMedio();
		        if (estoqueAtual == null && custo == null) {
		            estoqueAtual = 0;
		            custo = 0.0;
		        }
		        
		        Integer novaQuantidade = estoqueAtual + itemDTO.getQuantidade();
		        estoque.setInsumo(insumo);
		        estoque.setQuantidadeAtual(novaQuantidade);
		        Double custoMedio = ((estoqueAtual * custo) + (itemDTO.getQuantidade() * itemDTO.getValor_unitario())) / (estoqueAtual + itemDTO.getQuantidade());
		        estoque.setValorCustoMedio(custoMedio);
		        estoque.setData_registro(LocalDate.now());
		        estoqueRepository.save(estoque);
			}
			compra.setItensCompra(itensCompraList);
		}		
		return compra;
	}
	
	@Transactional
	public Compras update(Long id, @Valid ComprasDTO objDTO) {
		objDTO.setId(id);
		Compras oldObj = findById(id);
		updateCompra(oldObj, objDTO);
		LOGGER.info("Pedido atualizado com sucesso: " + oldObj.getId());
		return comprasRepository.save(oldObj);
	}

	private void updateCompra(Compras oldCompra, ComprasDTO objDTO) {
		// Atualiza os campos do pedido
		Tipo_Recebimento recebimento = recebimentoService.findById(objDTO.getTipo_recebimento_fk());
		oldCompra.setTipo_recebimento_fk(recebimento);
		oldCompra.setValor_total(objDTO.getValor_total());
		oldCompra.setData_registro(objDTO.getData_registro());
		oldCompra.setFornecedor(objDTO.getFornecedor());
		oldCompra.setNota_fiscal(objDTO.getNota_fiscal());
		
		// Status é mantido como está ou atualizado conforme sua lógica

		// Atualiza os itens do pedido
		List<ItensCompra> newItensCompraList = new ArrayList<>();

		for (ItensCompraDTO itemDTO : objDTO.getItensCompra()) {
			ItensCompra item;
			if (itemDTO.getId() != null) {
				// Busca o item existente para atualizar
				item = itensCompraService.findById(itemDTO.getId());
			} else {
				// Cria um novo item
				item = new ItensCompra();
				item.setCompra_fk(oldCompra);
			}

			Insumos insumo = insumosService.findById(itemDTO.getInsumo_fk());
			item.setInsumo_fk(insumo);
			item.setValor_unitario(itemDTO.getValor_unitario());
			item.setQuantidade(itemDTO.getQuantidade());

			newItensCompraList.add(item);
		}
		 // Identifica os itens que não estão na nova lista para removê-los
	    List<ItensCompra> itensParaRemover = oldCompra.getItensCompra().stream()
	            .filter(item -> !newItensCompraList.contains(item))
	            .collect(Collectors.toList());
	    
	 // Logs para depuração
        itensParaRemover.forEach(item -> LOGGER.info("Removendo item: " + item.getId()));

	    // Remove os itens do banco de dados
	    if (!itensParaRemover.isEmpty()) {
	        itensCompraRepository.deleteAll(itensParaRemover);
            LOGGER.info("Itens removidos do banco de dados: " + itensParaRemover.size());
	    }

		// Atualiza a lista de itens do pedido
		oldCompra.getItensCompra().clear();
		oldCompra.getItensCompra().addAll(newItensCompraList);
	}
}
