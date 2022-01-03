package com.educandoweb.projeto_spring.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.educandoweb.projeto_spring.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity()
@Table(name = "tb_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'z'", timezone = "GMT")
	private Instant moment;

	private Integer orderStatus;

	@ManyToOne // cardinalidade
	@JoinColumn(name = "client_id") // indica o nome da FK nesta tabela, pq é muitos para um no relacionamento
	private User client;

	@Setter(AccessLevel.NONE)
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	//atributo order no payment
	//o cascade é obrigatório, uma vez que tem o MapsId (mesmas ID no relacionamento 1:1)
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) 
	private Payment payment;

	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}
	
	public Double getTotal() {
		Double total = 0.0;
		for(OrderItem e : items) {
			total += e.getSubTotal();
		}
		return total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null)
			this.orderStatus = orderStatus.getCode();
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	

}
