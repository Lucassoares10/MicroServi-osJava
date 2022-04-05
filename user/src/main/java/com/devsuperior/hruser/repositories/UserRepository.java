package com.devsuperior.hruser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.hruser.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	//buscar de user por email, SPRING DATA JPA
	User findByEmail(String email);
}
