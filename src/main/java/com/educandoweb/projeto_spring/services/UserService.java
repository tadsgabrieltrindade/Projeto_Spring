package com.educandoweb.projeto_spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.projeto_spring.entities.User;
import com.educandoweb.projeto_spring.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll(); 
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get(); //retornar o objeto do tipo user que estiver dentro do optional
	}
	
	public User insert(User obj){
		return repository.save(obj); //ele retorna o objeto salvo no banco
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	//o Id para atualizar e o user para os novos dados
	public User update(Long id, User obj) {
		//instancia o usuário sem ir ao bd. Prepara o objeto monitorado para a manipulação
		User entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
}
