package com.daniel.brigadeiro.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daniel.brigadeiro.model.ItensPedido;
import com.daniel.brigadeiro.model.DTO.ItensPedidoDTO;
import com.daniel.brigadeiro.service.ItensPedidoService;

@Controller
@RequestMapping(value = "/itensPedido")
public class ItensPedidoController {
	
	@Autowired
	private ItensPedidoService itensPedidoService;

	public ItensPedidoController(ItensPedidoService itensPedidoService) {
		this.itensPedidoService = itensPedidoService;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ItensPedidoDTO> findById(@PathVariable Long id){
		ItensPedido obj = this.itensPedidoService.findById(id);
		return ResponseEntity.ok().body(new ItensPedidoDTO(obj));
	}
	
	  @GetMapping
	    public ResponseEntity<List<ItensPedidoDTO>> findAll(){
	        List<ItensPedido> list = itensPedidoService.findAll();
	        List<ItensPedidoDTO> listDTO = list.stream().map(ItensPedidoDTO::new).collect(Collectors.toList());
	        return ResponseEntity.ok().body(listDTO);
	    }
}
