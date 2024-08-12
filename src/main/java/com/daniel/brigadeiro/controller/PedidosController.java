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

import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.DTO.PedidosDTO;
import com.daniel.brigadeiro.service.PedidosService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/pedidos")
public class PedidosController {
	
	@Autowired
	PedidosService pedidosService;

	 public PedidosController(PedidosService pedidoService) {
	        this.pedidosService = pedidoService;
	    }
	
	 @GetMapping(value = "/{id}")
	public ResponseEntity<PedidosDTO> findById(@PathVariable Long id){
		Pedidos obj = this.pedidosService.findById(id);
		return ResponseEntity.ok().body(new PedidosDTO(obj));
	}
	
	  @GetMapping
	    public ResponseEntity<List<PedidosDTO>> findAll(){
	        List<Pedidos> list = pedidosService.findAll();
	        List<PedidosDTO> listDTO = list.stream().map(PedidosDTO::new).collect(Collectors.toList());
	        return ResponseEntity.ok().body(listDTO);
	    }
	  
	  @PostMapping
	    public ResponseEntity<String> create(@Valid @RequestBody PedidosDTO objDTO){
	        Pedidos obj = pedidosService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).body("Pedido cadastrado com sucesso!");
	    }
	     
	     @PutMapping(value = "/{id}")
	     public ResponseEntity<PedidosDTO> update(@PathVariable Long id, @Valid @RequestBody PedidosDTO objDTO){
	    	 Pedidos obj = pedidosService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new PedidosDTO(obj));
	     }
}
