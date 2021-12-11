package com.educandoweb.projeto_spring.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.educandoweb.projeto_spring.entities.Order;
import com.educandoweb.projeto_spring.entities.Product;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
 * O objetivo dessa classe é representar a chave composta no relacionamento Product
 * e Order. Lá a PK é a FK de product e a FK de Order.
 * */

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)


@Embeddable 	//indica que esta classe auxilia de PK composta
public class OrderItemPk implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@EqualsAndHashCode.Include
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
