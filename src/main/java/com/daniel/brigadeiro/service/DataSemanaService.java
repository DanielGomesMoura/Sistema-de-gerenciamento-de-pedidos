package com.daniel.brigadeiro.service;

import java.time.temporal.TemporalAdjusters;

import org.aspectj.lang.annotation.After;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DataSemanaService {

	  // Variáveis estáticas para armazenar as datas de início e fim da semana
    private static LocalDate dataInicioSemana;
    private static LocalDate dataFimSemana;
    
    // Método para obter o intervalo da semana (segunda a sexta-feira)
    public static LocalDate[] getIntervaloSemana() {
        LocalDate hoje = LocalDate.now();

        // Se hoje é domingo ou as datas ainda não foram definidas, recalcular
        if (hoje.getDayOfWeek() == DayOfWeek.SUNDAY || dataInicioSemana == null || dataFimSemana == null) {
            calcularIntervaloSemana(hoje);
        }

        return new LocalDate[]{dataInicioSemana, dataFimSemana};
    }
    

    // Método privado para calcular a data de início (segunda-feira) e fim (sexta-feira)
    private static void calcularIntervaloSemana(LocalDate hoje) {
    	if(hoje.getDayOfWeek().compareTo(DayOfWeek.MONDAY) > 0 && hoje.getDayOfWeek().compareTo(DayOfWeek.FRIDAY) <= 0) {
    		dataInicioSemana = hoje.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    	}
    	else{
    		dataInicioSemana = hoje.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
    	}
		 dataFimSemana = hoje.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
    }
}