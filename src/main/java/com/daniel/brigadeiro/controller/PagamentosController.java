package com.daniel.brigadeiro.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.daniel.brigadeiro.model.DTO.PagamentosLoteDTO;
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
	    public ResponseEntity<PagamentosDTO> create(@Valid @RequestBody PagamentosDTO objDTO){
		 Pagamentos obj = pagamentosService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	    }
	 
	    @PutMapping(value = "/{id}")
	     public ResponseEntity<PagamentosDTO> update(@PathVariable Long id, @Valid @RequestBody PagamentosDTO objDTO){
	    	Pagamentos obj = pagamentosService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new PagamentosDTO(obj));
	     }
	    
	    @PostMapping("/pagar-lote")
	    public ResponseEntity<String> pagarPedidosEmLote(@RequestBody PagamentosLoteDTO pagamentosLoteDTO) {
	        try {
	            pagamentosService.realizarPagamentoEmLote(pagamentosLoteDTO);
	            return ResponseEntity.ok("Pagamento em lote realizado com sucesso!");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Erro ao realizar o pagamento: " + e.getMessage());
	        }
	    }
}
