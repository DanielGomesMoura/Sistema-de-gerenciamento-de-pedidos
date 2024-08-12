package com.daniel.brigadeiro.controller;

import java.net.URI;

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

import com.daniel.brigadeiro.model.Pagamentos;
import com.daniel.brigadeiro.model.DTO.PagamentosDTO;
import com.daniel.brigadeiro.service.PagamentosService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/pagamentos")
public class PagamentosController {
	
	@Autowired
	PagamentosService pagamentosService;
	
	
	 @GetMapping(value = "/{id}")
		public ResponseEntity<PagamentosDTO> findById(@PathVariable Long id){
			Pagamentos obj = this.pagamentosService.findById(id);
			return ResponseEntity.ok().body(new PagamentosDTO(obj));
		}
	 
	 @PostMapping
	    public ResponseEntity<String> create(@Valid @RequestBody PagamentosDTO objDTO){
		 Pagamentos obj = pagamentosService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).body("Pedido cadastrado com sucesso!");
	    }
	 
	    @PutMapping(value = "/{id}")
	     public ResponseEntity<PagamentosDTO> update(@PathVariable Long id, @Valid @RequestBody PagamentosDTO objDTO){
	    	Pagamentos obj = pagamentosService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new PagamentosDTO(obj));
	     }
}
