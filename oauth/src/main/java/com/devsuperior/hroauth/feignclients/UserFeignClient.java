package com.devsuperior.hroauth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devsuperior.hroauth.entities.User;

@Component
@FeignClient(name = "hr-user", path = "/users")  //cliente feign, chamando como /users
public interface UserFeignClient {

	//assinatura do findByEmail do user
	@GetMapping(value = "/search")
	ResponseEntity<User> findByEmail(@RequestParam String email);	
}
