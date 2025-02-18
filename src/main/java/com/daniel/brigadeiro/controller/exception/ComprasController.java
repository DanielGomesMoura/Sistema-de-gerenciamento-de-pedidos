package com.daniel.brigadeiro.controller.exception;

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

import com.daniel.brigadeiro.model.Compras;
import com.daniel.brigadeiro.model.DTO.ComprasDTO;
import com.daniel.brigadeiro.service.ComprasService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/compras")
public class ComprasController {

	@Autowired
	private ComprasService comprasService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ComprasDTO> findById(@PathVariable Long id){
		Compras obj = this.comprasService.findById(id);
		return ResponseEntity.ok().body(new ComprasDTO(obj));
	}
	
	  @GetMapping
	    public ResponseEntity<List<ComprasDTO>> findAll(){
	        List<Compras> list = comprasService.findAll();
	        List<ComprasDTO> listDTO = list.stream().map(ComprasDTO::new).collect(Collectors.toList());
	        return ResponseEntity.ok().body(listDTO);
	    }
	  
	  @PostMapping
	    public ResponseEntity<ComprasDTO> create(@Valid @RequestBody ComprasDTO objDTO){
		  Compras obj = comprasService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	    }
	     
	     @PutMapping(value = "/{id}")
	     public ResponseEntity<ComprasDTO> update(@PathVariable Long id, @Valid @RequestBody ComprasDTO objDTO){
	    	 Compras obj = comprasService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new ComprasDTO(obj));
	     }
	     
}
