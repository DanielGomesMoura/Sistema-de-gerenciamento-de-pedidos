package com.daniel.brigadeiro.service.EmailSchedulerService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.daniel.brigadeiro.service.EmailService;
import com.daniel.brigadeiro.service.RelatorioService;

@Service
public class EmailSchedulerService {

	@Autowired
    private RelatorioService reportService;
	
	@Autowired
	private EmailService emailService;
  

    @Scheduled(cron = "0 40 16 * * ?")
    public void enviarRelatorioEmail() {
    	String reportName = "relatorio_venda_diaria"; // Nome do relatório agendado
        String recipientEmail = "anabeatriz.pereirasantos1997@gmail.com"; // Endereço de e-mail do destinatário

        try {
            byte[] reportData = reportService.generateReport(reportName);
            Path path = Files.write(Paths.get(reportName + ".pdf"), reportData);

            emailService.sendEmailWithAttachment(recipientEmail, "Relatório Agendado", "Segue em anexo o relatório agendado.", path.toString());

            System.out.println("E-mail enviado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
        }

    }
}
