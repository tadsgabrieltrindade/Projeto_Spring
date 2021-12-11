package com.educandoweb.projeto_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.projeto_spring.entities.Category;
import com.educandoweb.projeto_spring.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
