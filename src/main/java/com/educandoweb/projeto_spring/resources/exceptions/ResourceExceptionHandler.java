package com.educandoweb.projeto_spring.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.projeto_spring.services.exceptions.DatabaseException;
import com.educandoweb.projeto_spring.services.exceptions.ResourceNotFoundException;

@ControllerAdvice //Permite interceptar as possíveis exceções a fim de executar algum tipo de tratamento
public class ResourceExceptionHandler {

	/*
	 * 1ºP: recebe uma exceção do tipo personalizado
	 * 2ºP: serve para pegar o caminho do recurso na qual foi lançado a exceção
	 * A annotation irá interceptar exceções lançadas pelo ResourceNotFoundException e irá tratar no método
	 * */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found"; //mensagem de erro básica, como se fosse um título
		HttpStatus status = HttpStatus.NOT_FOUND; //código 404 not found
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		/*
		 * .status para retornar com um código personalizado
		 * */
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> dataBase(DatabaseException e, HttpServletRequest request){
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, "user associated with an order", request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
}
