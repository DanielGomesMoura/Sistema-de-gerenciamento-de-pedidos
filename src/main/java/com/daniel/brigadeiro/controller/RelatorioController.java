package com.daniel.brigadeiro.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daniel.brigadeiro.service.EmailService;
import com.daniel.brigadeiro.service.RelatorioService;

@Controller
@RequestMapping(value = "/relatorio")
public class RelatorioController {

	 @Autowired
	    private RelatorioService reportService;
	 
	  @Autowired
	    private EmailService emailService;
	 
	   @GetMapping("/{reportName}")
	     public ResponseEntity<byte[]> viewReport(@PathVariable String reportName) {
	         try {
	             byte[] reportData = reportService.generateReport(reportName);
	             HttpHeaders headers = new HttpHeaders();
	             headers.setContentType(MediaType.APPLICATION_PDF);
	             return ResponseEntity.ok().headers(headers).body(reportData);
	         } catch (Exception e) {
	        	 System.out.println(e);
	             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	         }
	     }
	     
	     @GetMapping("/relatorio_saldo/{id}")
	     public ResponseEntity<byte[]> ImprimirSaldo(@PathVariable Long id) {
	         try {
	        	 // Cria o mapa de parâmetros
	             Map<String, Object> parametros = new HashMap<>();
	             parametros.put("id", id);

	             byte[] reportData = reportService.generateReport("relatorio_saldo_cliene", parametros);
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
	        
	             // Obter o caminho baseado no sistema operacional
	             Path downloadPath = Paths.get("/app/relatorios", reportName + ".pdf");
	             
	             // Garantir que a pasta existe
	             Files.createDirectories(downloadPath.getParent());

	             Files.write(downloadPath, reportData);

	             emailService.sendEmailWithAttachment(to, "Seu Relatório", "Segue em anexo o relatório solicitado.", downloadPath.toString());
	             return ResponseEntity.ok("E-mail enviado com sucesso!");
	         } catch (Exception e) {
	             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar e-mail: " + e.getMessage());
	         }
	     }
}
