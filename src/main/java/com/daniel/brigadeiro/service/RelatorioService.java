package com.daniel.brigadeiro.service;

import java.io.InputStream;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class RelatorioService {

	  @Autowired
	  private DataSource dataSource;

	  public byte[] generateReport(String reportName) throws Exception {
	        InputStream reportStream = getClass().getResourceAsStream("/relatorios/" + reportName + ".jasper");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, null, dataSource.getConnection());
	        return JasperExportManager.exportReportToPdf(jasperPrint);
	    }
}
