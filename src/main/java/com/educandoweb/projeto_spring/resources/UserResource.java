package com.educandoweb.projeto_spring.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.projeto_spring.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll() {
		User u = new User(21L, "Gabriel", "gabriel.Trindade@gmail.com", "326596565", "Lucian21kjl2");
		return ResponseEntity.ok(u);
	}
}
