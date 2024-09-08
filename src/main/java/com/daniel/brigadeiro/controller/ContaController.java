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

import com.daniel.brigadeiro.model.Conta;
import com.daniel.brigadeiro.model.DTO.ContaDTO;
import com.daniel.brigadeiro.service.ContaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContaDTO> findById(@PathVariable Long id){
		Conta obj = this.contaService.findById(id);
		return ResponseEntity.ok().body(new ContaDTO(obj));
	}
	
	  @GetMapping
	    public ResponseEntity<List<ContaDTO>> findAll(){
	        List<Conta> list = contaService.findAll();
	        List<ContaDTO> listDTO = list.stream().map(ContaDTO::new).collect(Collectors.toList());
	        return ResponseEntity.ok().body(listDTO);
	    }
	  
	  @PostMapping
	    public ResponseEntity<ContaDTO> create(@Valid @RequestBody ContaDTO objDTO){
	        Conta obj = contaService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	    }
	     
	     @PutMapping(value = "/{id}")
	     public ResponseEntity<ContaDTO> update(@PathVariable Long id, @Valid @RequestBody ContaDTO objDTO){
	    	 Conta obj = contaService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new ContaDTO(obj));
	     }
	     
	     @DeleteMapping(value = "/{id}")
	 	public ResponseEntity<ContaDTO> delete(@PathVariable Long id){
	    	 contaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
