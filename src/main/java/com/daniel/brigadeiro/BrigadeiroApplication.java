package com.daniel.brigadeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BrigadeiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrigadeiroApplication.class, args);
	}

}
