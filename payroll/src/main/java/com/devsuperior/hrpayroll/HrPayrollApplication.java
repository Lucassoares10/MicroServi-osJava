package com.devsuperior.hrpayroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker //habilitando o Hystrix para tratar tolerancia a falhas
@EnableEurekaClient //habilitando o eurekaclient no project, ele ja vem o balanceamento de carga
@EnableFeignClients //habilitando a aplicação para user o Feign
@SpringBootApplication
public class HrPayrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrPayrollApplication.class, args);
	}

}
