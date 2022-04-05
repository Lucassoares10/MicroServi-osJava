package com.devsuperior.hrworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;

@RefreshScope //Adicionando o actuator para quando for att configurações em runtime
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
	
	//objeto que serve para imprimir no log da aplicação
	private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);
	
	//variavel para o log ler, test config, mesmo do Server config no git
	@Value("${test.config}")
	private String testConfig;
	
	//injetando dependencia,Environment tem varias informações do contexto da aplicação
	//variaveis de configuração etc...
	@Autowired
	private Environment env;
	
	@Autowired
	private WorkerRepository repository;
	
	//end point para pegar as configurações do projeto, no server config que ira conectar com
	//o git hub e ira pegar o que esta atribuido na variavel testConfig
	@GetMapping(value = "/configs")
	public ResponseEntity<Void> getConfigs() {
		logger.info("CONFIG = " + testConfig);
		return ResponseEntity.noContent().build();
	}		
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {
		List<Worker> list = repository.findAll();
		return ResponseEntity.ok(list);
	}	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) { 
		
		/*
		//sleep para demorar 3s para fazer a chamada
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		
		//para imprimir uma mensagem no console da aplicação, para pegar e imprimir
		//as portas que foram configuradas nos arquivos de configuração do Appp
		logger.info("PORT = " + env.getProperty("local.server.port"));
		
		Worker obj = repository.findById(id).get();
		return ResponseEntity.ok(obj);
	}	
}
