package com.educandoweb.projeto_spring.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.projeto_spring.entities.User;
import com.educandoweb.projeto_spring.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}") //indica que aceita um id dentro da URL
	public ResponseEntity<User> findById(@PathVariable Long id){ //passa a var (id) para a URL
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//Inserir user no banco
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = service.insert(obj);
		
		//Forma no SpringBoot para gerar o endereço na qual o novo recurso pode ser recuperado 
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}") //recebe um padrão para montar a URL, com caminho /users e o /id
				.buildAndExpand(obj.getId()) //Espera que informe o ID do objeto que inserir, para ficar no /id
				.toUri();  //Para converter para objeto do tipo URI
		
		//created espera o Location URI (Header HTTP)
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){ //para ser reconhecido como variável da URL
		service.delete(id);
		return ResponseEntity
				.noContent() //retorna o código 204. Ok, mas sem conteúdo!
				.build();
	}
}
