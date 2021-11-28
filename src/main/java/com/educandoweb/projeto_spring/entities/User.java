package com.educandoweb.projeto_spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter()
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@RequiredArgsConstructor //outra forma de o lombok construir o construtor com atributos especificados
@Entity		//depender da especificação
@Table(name = "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@NonNull //Uma forma de indicar que este atributo deve estar no construtor
	private Long id;

	//@NonNull
	private String name;
	
	//@NonNull
	private String email;
	
	//@NonNull
	private String phone;
	
	//@NonNull
	private String password;
	
	@Setter(AccessLevel.NONE) //não cria um setter para este atributo, somente o getter
	@OneToMany(mappedBy = "client") //indica qual é o atributo que está mapeado na entidade Order
	@JsonIgnore //evita o loop 
	private List<Order> orders = new ArrayList<>();
	
	
	public User() {
	}


	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}
	
	
}
