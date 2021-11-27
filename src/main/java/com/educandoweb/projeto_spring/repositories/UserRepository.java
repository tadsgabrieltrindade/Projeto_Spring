package com.educandoweb.projeto_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.projeto_spring.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
