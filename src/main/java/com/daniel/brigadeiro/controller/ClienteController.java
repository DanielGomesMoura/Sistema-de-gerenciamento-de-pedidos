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

import com.daniel.brigadeiro.model.Clientes;
import com.daniel.brigadeiro.model.DTO.ClientesDTO;
import com.daniel.brigadeiro.service.ClientesService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClientesService clientesService;
	
	 public ClienteController(ClientesService clientesService) {
	        this.clientesService = clientesService;
	    }
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientesDTO> findById(@PathVariable Long id){
		Clientes obj = this.clientesService.findById(id);
		return ResponseEntity.ok().body(new ClientesDTO(obj));
	}
	
	  @GetMapping
	    public ResponseEntity<List<ClientesDTO>> findAll(){
	        List<Clientes> list = clientesService.findAll();
	        List<ClientesDTO> listDTO = list.stream().map(ClientesDTO::new).collect(Collectors.toList());
	        return ResponseEntity.ok().body(listDTO);
	    }
	  
	  @PostMapping
	    public ResponseEntity<ClientesDTO> create(@Valid @RequestBody ClientesDTO objDTO){
	        Clientes obj = clientesService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	    }
	     
	     @PutMapping(value = "/{id}")
	     public ResponseEntity<ClientesDTO> update(@PathVariable Long id, @Valid @RequestBody ClientesDTO objDTO){
	    	 Clientes obj = clientesService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new ClientesDTO(obj));
	     }
	     
	     @DeleteMapping(value = "/{id}")
	 	public ResponseEntity<ClientesDTO> delete(@PathVariable Long id){
	    	 clientesService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
