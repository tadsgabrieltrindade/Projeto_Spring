package com.educandoweb.projeto_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.projeto_spring.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
