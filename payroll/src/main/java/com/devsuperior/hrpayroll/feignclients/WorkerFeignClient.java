package com.devsuperior.hrpayroll.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.devsuperior.hrpayroll.entities.Worker;

//interface para declarar as requisições que seram feitas pelos mc
@Component
//client feign do projeto worker, comunicação com outro MC
@FeignClient(name = "hr-worker", path = "/workers")  
public interface WorkerFeignClient {

	//worker resource do outro mc para fazer a chamada webservice do metodo.
	@GetMapping(value = "/{id}")
	ResponseEntity<Worker> findById(@PathVariable Long id);
}
