package com.meuprojeto.meuapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MeuappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuappApplication.class, args);
	}

}
