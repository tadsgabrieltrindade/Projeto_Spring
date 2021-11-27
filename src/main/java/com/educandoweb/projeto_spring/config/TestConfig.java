package com.educandoweb.projeto_spring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.projeto_spring.entities.User;
import com.educandoweb.projeto_spring.repositories.UserRepository;

@Configuration

//está relacionado com o spring.profiles.active no application.properties, rodando somente
//qnd estiver no perfil de 'test'
@Profile("test")  
public class TestConfig implements CommandLineRunner {
	
	@Autowired //injeção de dependência
	private UserRepository userRepository;

	//tudo o que estiver dentro deste método será executado qnd a aplicação for iniciada
	@Override	
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
	}
	
	
}
