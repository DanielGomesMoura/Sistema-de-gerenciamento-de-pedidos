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

import com.daniel.brigadeiro.model.Produtos;
import com.daniel.brigadeiro.model.DTO.ProdutosDTO;
import com.daniel.brigadeiro.service.ProdutosService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/produtos")
public class ProdutosController {

	@Autowired
	private ProdutosService produtoService;
	
	 public ProdutosController(ProdutosService produtoService) {
	        this.produtoService = produtoService;
	    }
	
	 @GetMapping(value = "/{id}")
	public ResponseEntity<ProdutosDTO> findById(@PathVariable Long id){
		Produtos obj = this.produtoService.findById(id);
		return ResponseEntity.ok().body(new ProdutosDTO(obj));
	}
	
	  @GetMapping
	    public ResponseEntity<List<ProdutosDTO>> findAll(){
	        List<Produtos> list = produtoService.findAll();
	        List<ProdutosDTO> listDTO = list.stream().map(ProdutosDTO::new).collect(Collectors.toList());
	        return ResponseEntity.ok().body(listDTO);
	    }
	  
	  @PostMapping
	    public ResponseEntity<ProdutosDTO> create(@Valid @RequestBody ProdutosDTO objDTO){
	        Produtos obj = produtoService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	    }
	     
	     @PutMapping(value = "/{id}")
	     public ResponseEntity<ProdutosDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutosDTO objDTO){
	    	 Produtos obj = produtoService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new ProdutosDTO(obj));
	     }
	     
	     @DeleteMapping(value = "/{id}")
	 	public ResponseEntity<ProdutosDTO> delete(@PathVariable Long id){
	    	 produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
