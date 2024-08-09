package com.daniel.brigadeiro.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniel.brigadeiro.model.Clientes;
import com.daniel.brigadeiro.model.ItensPedido;
import com.daniel.brigadeiro.model.DTO.ClientesDTO;
import com.daniel.brigadeiro.model.DTO.ItensPedidoDTO;
import com.daniel.brigadeiro.service.ItensPedidoService;

import jakarta.validation.Valid;

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
	  
	  @PostMapping
	    public ResponseEntity<String> create(@Valid @RequestBody ItensPedidoDTO objDTO){
		  ItensPedido obj = itensPedidoService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).body("Itens do Pedido cadastrado com sucesso!");
	    }
	     
	     @PutMapping(value = "/{id}")
	     public ResponseEntity<ItensPedidoDTO> update(@PathVariable Long id, @Valid @RequestBody ItensPedidoDTO objDTO){
	    	 ItensPedido obj = itensPedidoService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new ItensPedidoDTO(obj));
	     }
}
