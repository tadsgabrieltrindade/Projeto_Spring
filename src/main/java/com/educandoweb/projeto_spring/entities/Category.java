package com.educandoweb.projeto_spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity 
@Table(name = "tb_category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	@JsonIgnore //serve para não ficar em loop, ou seja, um atributo fica chamando o outro
	@Setter(AccessLevel.NONE) //não cria o set deste atributo
	/*
	 * Explicado na entidade Produto, pois é uma relação muito para muitos e é necessário criar uma nova tabela
	 * relacionameto as duas entidade, Category e Product.
	 * */
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products = new HashSet<>();
	
	
	public Category() {
	}

	public Category(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
}
