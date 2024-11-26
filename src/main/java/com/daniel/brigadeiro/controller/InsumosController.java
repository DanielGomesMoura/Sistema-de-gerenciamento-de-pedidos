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

import com.daniel.brigadeiro.model.Insumos;
import com.daniel.brigadeiro.model.DTO.InsumosDTO;
import com.daniel.brigadeiro.service.InsumosService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/insumos")
public class InsumosController {
	
	@Autowired
	private InsumosService insumosService;
	
	 public InsumosController(InsumosService insumosService) {
	        this.insumosService = insumosService;
	    }
	
	 @GetMapping(value = "/{id}")
	public ResponseEntity<InsumosDTO> findById(@PathVariable Long id){
		Insumos obj = this.insumosService.findById(id);
		return ResponseEntity.ok().body(new InsumosDTO(obj));
	}
	
	  @GetMapping
	    public ResponseEntity<List<InsumosDTO>> findAll(){
	        List<Insumos> list = insumosService.findAll();
	        List<InsumosDTO> listDTO = list.stream().map(InsumosDTO::new).collect(Collectors.toList());
	        return ResponseEntity.ok().body(listDTO);
	    }
	  
	  @PostMapping
	    public ResponseEntity<InsumosDTO> create(@Valid @RequestBody InsumosDTO objDTO){
	        Insumos obj = insumosService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	    }
	     
	     @PutMapping(value = "/{id}")
	     public ResponseEntity<InsumosDTO> update(@PathVariable Long id, @Valid @RequestBody InsumosDTO objDTO){
	    	 Insumos obj = insumosService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new InsumosDTO(obj));
	     }
	     
	     @DeleteMapping(value = "/{id}")
	 	public ResponseEntity<InsumosDTO> delete(@PathVariable Long id){
	    	 insumosService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
