package com.educandoweb.projeto_spring.services.exceptions;

//RuntimeException não obriga a tratar
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. ID " + id);
	}
}
 