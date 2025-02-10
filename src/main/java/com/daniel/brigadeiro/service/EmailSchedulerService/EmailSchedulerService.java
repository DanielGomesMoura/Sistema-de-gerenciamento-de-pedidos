package com.daniel.brigadeiro.service.EmailSchedulerService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailSchedulerService {

//	private final RestTemplate restTemplate;
//    private final String loginUrl = "http://localhost:8080/login";
//    private final String email = "seu_email@example.com";
//    private final String senha = "sua_senha";
//    private String jwtToken;
//
//    public EmailSchedulerService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//        this.jwtToken = obterTokenJwt();
//    }
//    
//    private String obterTokenJwt() {
//        // Credenciais de login
//        String requestJson = String.format("{\"email\": \"%s\", \"senha\": \"%s\"}", email, senha);
//        
//        // Configurar cabeçalhos HTTP para a requisição de login
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json");
//        
//        // Criar entidade HTTP com as credenciais de login
//        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
//        
//        // Fazer a requisição POST para obter o token JWT
//        ResponseEntity<String> response = restTemplate.exchange(loginUrl, HttpMethod.POST, entity, String.class);
//        
//        // Supondo que o token esteja no corpo da resposta
//        String token = response.getBody();
//        
//        return token;
//    }
//
//    @Scheduled(cron = "0 40 16 * * ?")
//    public void enviarRelatorioEmail() {
//        String url = "http://localhos://8080/pedidos/relatorio_venda_diaria";
//
//        // Configurar cabeçalhos HTTP com o token JWT
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + jwtToken);
//
//        // Criar entidade HTTP com os cabeçalhos
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        // Fazer a requisição POST com o token JWT
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//
//        // Verificar a resposta da requisição
//        if (response.getStatusCode().is2xxSuccessful()) {
//            System.out.println("Relatório enviado com sucesso às 16:40");
//        } else {
//            System.out.println("Erro ao enviar o relatório: " + response.getStatusCode());
//        }
//    }
}
