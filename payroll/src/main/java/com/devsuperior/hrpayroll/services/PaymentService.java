package com.devsuperior.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import com.devsuperior.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {
	
	//injetando a dependencia do feign client.
	@Autowired
	private WorkerFeignClient workerFeignClient;

	//metodo que busca o pagamento do trabalhador pelo id, getBody porque o corpo
	 //da resposta é um ResponseEntity,o getbody é para ele transformar a resposta em worker
	public Payment getPayment(long workerId, int days) { 
		//chamando a interface worker e o metodo.
		Worker worker = workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}
