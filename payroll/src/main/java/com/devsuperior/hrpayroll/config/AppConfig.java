package com.devsuperior.hrpayroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	//metodo restTemplate para ser injetado em outros componentes
	//ele serve para registrar uma instancia unica de um objeto
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
