package com.educandoweb.projeto_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.projeto_spring.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
}
