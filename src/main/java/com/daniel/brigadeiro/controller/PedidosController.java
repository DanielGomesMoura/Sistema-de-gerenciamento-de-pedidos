package com.daniel.brigadeiro.controller;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniel.brigadeiro.model.Pedidos;
import com.daniel.brigadeiro.model.DTO.ClientesDTO;
import com.daniel.brigadeiro.model.DTO.PedidosDTO;
import com.daniel.brigadeiro.model.DTO.RankDTO;
import com.daniel.brigadeiro.service.EmailService;
import com.daniel.brigadeiro.service.PedidosService;
import com.daniel.brigadeiro.service.RelatorioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/pedidos")
public class PedidosController {
	
	@Autowired
	PedidosService pedidosService;

    @Autowired
    private RelatorioService reportService;
    
    @Autowired
    private EmailService emailService;

	 @GetMapping(value = "/{id}")
	public ResponseEntity<PedidosDTO> findById(@PathVariable Long id){
		Pedidos obj = this.pedidosService.findById(id);
		return ResponseEntity.ok().body(new PedidosDTO(obj));
	}
	 
	     @GetMapping("/view/{reportName}")
	     public ResponseEntity<byte[]> viewReport(@PathVariable String reportName) {
	         try {
	             byte[] reportData = reportService.generateReport(reportName);
	             HttpHeaders headers = new HttpHeaders();
	             headers.setContentType(MediaType.APPLICATION_PDF);
	             return ResponseEntity.ok().headers(headers).body(reportData);
	         } catch (Exception e) {
	             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	         }
	     }
	     
	     @PostMapping("/email/{reportName}")
	     public ResponseEntity<String> emailReport(@PathVariable String reportName, @RequestParam String to) {
	         try {
	             byte[] reportData = reportService.generateReport(reportName);
	             Path path = Files.write(Paths.get(reportName + ".pdf"), reportData);

	             emailService.sendEmailWithAttachment(to, "Seu Relatório", "Segue em anexo o relatório solicitado.", path.toString());
	             return ResponseEntity.ok("E-mail enviado com sucesso!");
	         } catch (Exception e) {
	             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar e-mail: " + e.getMessage());
	         }
	     }
	 
	 @GetMapping()
	    public ResponseEntity<List<PedidosDTO>> findAll( @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
	            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFinal, @RequestParam String situacao){
	        List<Pedidos> list = pedidosService.findByDay(dataInicio, dataFinal,situacao);
	        List<PedidosDTO> listDTO = list.stream().map(PedidosDTO::new).collect(Collectors.toList());
	        return ResponseEntity.ok().body(listDTO);
	    }
	
//	  @GetMapping
//	    public ResponseEntity<List<PedidosDTO>> findAll(){
//	        List<Pedidos> list = pedidosService.findAll();
//	        List<PedidosDTO> listDTO = list.stream().map(PedidosDTO::new).collect(Collectors.toList());
//	        return ResponseEntity.ok().body(listDTO);
//	    }
	  
	  @GetMapping("ranking")
	    public ResponseEntity<List<RankDTO>> Ranking(){
		  List<RankDTO> listDTO = pedidosService.ranking();
		    return ResponseEntity.ok().body(listDTO);
	    }
	  
	  @PostMapping
	    public ResponseEntity<ClientesDTO> create(@Valid @RequestBody PedidosDTO objDTO){
	        Pedidos obj = pedidosService.create(objDTO);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	    }
	     
	     @PutMapping(value = "/{id}")
	     public ResponseEntity<PedidosDTO> update(@PathVariable Long id, @Valid @RequestBody PedidosDTO objDTO){
	    	 Pedidos obj = pedidosService.update(id, objDTO);
	    	 return ResponseEntity.ok().body(new PedidosDTO(obj));
	     }
}
