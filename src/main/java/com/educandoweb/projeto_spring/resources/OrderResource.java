package com.educandoweb.projeto_spring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.projeto_spring.entities.Order;
import com.educandoweb.projeto_spring.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}") //indica que aceita um id dentro da URL
	public ResponseEntity<Order> findById(@PathVariable Long id){ //passa a var (id) para a URL
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
