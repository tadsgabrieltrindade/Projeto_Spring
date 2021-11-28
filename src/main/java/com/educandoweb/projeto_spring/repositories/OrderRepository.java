package com.educandoweb.projeto_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.projeto_spring.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
