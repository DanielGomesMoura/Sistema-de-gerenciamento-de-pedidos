package com.daniel.brigadeiro.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniel.brigadeiro.model.Movimento_Caixa;
import com.daniel.brigadeiro.model.Tipo_Recebimento;
import com.daniel.brigadeiro.model.DTO.Movimento_CaixaDTO;
import com.daniel.brigadeiro.service.Movimento_CaixaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/movimentos")
public class Movimento_CaixaController {
	
	@Autowired
	private Movimento_CaixaService caixaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Movimento_CaixaDTO> findById(@PathVariable Long id){
		Movimento_Caixa obj = this.caixaService.findById(id);
		return ResponseEntity.ok().body(new Movimento_CaixaDTO(obj));
	}
	
	  @GetMapping
	    public ResponseEntity<List<Movimento_CaixaDTO>> findAll(){
	        List<Movimento_Caixa> list = caixaService.findAll();
	        List<Movimento_CaixaDTO> listDTO = list.stream().map(Movimento_CaixaDTO::new).collect(Collectors.toList());
	        return ResponseEntity.ok().body(listDTO);
	    }
	  
	  @PostMapping
	    public ResponseEntity<Movimento_CaixaDTO> create(@Valid @RequestBody Movimento_CaixaDTO objDTO){
	    	Movimento_Caixa obj = caixaService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	    }
	     
	     @PutMapping(value = "/{id}")
	     public ResponseEntity<Movimento_CaixaDTO> update(@PathVariable Long id, @Valid @RequestBody Movimento_CaixaDTO objDTO){
	    	 Movimento_Caixa obj = caixaService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new Movimento_CaixaDTO(obj));
	     }
	     
	     @DeleteMapping(value = "/{id}")
	 	public ResponseEntity<Movimento_CaixaDTO> delete(@PathVariable Long id){
	 		caixaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
