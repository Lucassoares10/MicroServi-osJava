package com.devsuperior.hrconfigserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer  //habilitando o config server para pegar configurações do mc remotamente
@SpringBootApplication
public class HrConfigServerApplication implements CommandLineRunner {

	//pegando a variavel de ambiente USERNAME
	@Value("${spring.cloud.config.server.git.username}")
	private String username;
	
	public static void main(String[] args) {
		SpringApplication.run(HrConfigServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//para verificar se realmente esta pegando a variavel de ambiente
		//System.out.println("USERNAME = " + username);
	}
}
