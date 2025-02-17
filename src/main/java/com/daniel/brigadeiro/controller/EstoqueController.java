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

import com.daniel.brigadeiro.model.Estoque;
import com.daniel.brigadeiro.model.DTO.EstoqueDTO;
import com.daniel.brigadeiro.service.EstoqueService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/estoque")
public class EstoqueController {
	
	@Autowired
	private EstoqueService estoqueService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EstoqueDTO> findById(@PathVariable Long id){
		Estoque obj = this.estoqueService.findById(id);
		return ResponseEntity.ok().body(new EstoqueDTO(obj));
	}
	
	  @GetMapping
	    public ResponseEntity<List<EstoqueDTO>> findAll(){
	        List<Estoque> list = estoqueService.findAll();
	        List<EstoqueDTO> listDTO = list.stream().map(EstoqueDTO::new).collect(Collectors.toList());
	        return ResponseEntity.ok().body(listDTO);
	    }
	  
	  @PostMapping
	    public ResponseEntity<EstoqueDTO> create(@Valid @RequestBody EstoqueDTO objDTO){
		  Estoque obj = estoqueService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	    }
	     
	     @PutMapping(value = "/{id}")
	     public ResponseEntity<EstoqueDTO> update(@PathVariable Long id, @Valid @RequestBody EstoqueDTO objDTO){
	    	 Estoque obj = estoqueService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new EstoqueDTO(obj));
	     }

}
