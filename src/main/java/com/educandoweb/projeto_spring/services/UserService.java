package com.educandoweb.projeto_spring.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.projeto_spring.entities.User;
import com.educandoweb.projeto_spring.repositories.UserRepository;
import com.educandoweb.projeto_spring.services.exceptions.DatabaseException;
import com.educandoweb.projeto_spring.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);

		// return obj.get(); //retornar o objeto do tipo user que estiver dentro do
		// optional

		// ele irá tentar o get, caso não conseguir lança a exceção
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {
		return repository.save(obj); // ele retorna o objeto salvo no banco
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);  //qnd o recurso não é encontrado
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage()); //qnd o recurso está dependendo de outro 

		}
	}

	// o Id para atualizar e o user para os novos dados
	public User update(Long id, User obj) {
		try {
			// instancia o usuário sem ir ao bd. Prepara o objeto monitorado para a
			// manipulação
			User entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());

	}
}
