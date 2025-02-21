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

import com.daniel.brigadeiro.model.Tipo_Recebimento;
import com.daniel.brigadeiro.model.DTO.Tipo_RecebimentoDTO;
import com.daniel.brigadeiro.projection.TipoRecebimentoProjection;
import com.daniel.brigadeiro.service.Tipo_RecebimentoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/recebimentos")
public class Tipo_RecebimentoController {

	@Autowired
	private Tipo_RecebimentoService recebimentoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Tipo_RecebimentoDTO> findById(@PathVariable Long id){
		Tipo_Recebimento obj = this.recebimentoService.findById(id);
		return ResponseEntity.ok().body(new Tipo_RecebimentoDTO(obj));
	}
	
	  @GetMapping
	    public ResponseEntity<List<Tipo_RecebimentoDTO>> findAll(){
	        List<Tipo_Recebimento> list = recebimentoService.findAll();
	        List<Tipo_RecebimentoDTO> listDTO = list.stream().map(Tipo_RecebimentoDTO::new).collect(Collectors.toList());
	        return ResponseEntity.ok().body(listDTO);
	    }
	  
	  @GetMapping(value = "/combo/{tipo}/{categoria}")
	    public ResponseEntity<List<TipoRecebimentoProjection>> findByTipoRecebimento(@PathVariable String tipo, @PathVariable String categoria){
	        List<TipoRecebimentoProjection> list = recebimentoService.findByTipoRecebimento(tipo, categoria);
	        return ResponseEntity.ok().body(list);
	    }
	  
	  @PostMapping
	    public ResponseEntity<Tipo_RecebimentoDTO> create(@Valid @RequestBody Tipo_RecebimentoDTO objDTO){
	        Tipo_Recebimento obj = recebimentoService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	    }
	     
	     @PutMapping(value = "/{id}")
	     public ResponseEntity<Tipo_RecebimentoDTO> update(@PathVariable Long id, @Valid @RequestBody Tipo_RecebimentoDTO objDTO){
	    	 Tipo_Recebimento obj = recebimentoService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new Tipo_RecebimentoDTO(obj));
	     }
	     
	     @DeleteMapping(value = "/{id}")
	 	public ResponseEntity<Tipo_RecebimentoDTO> delete(@PathVariable Long id){
	 		recebimentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
