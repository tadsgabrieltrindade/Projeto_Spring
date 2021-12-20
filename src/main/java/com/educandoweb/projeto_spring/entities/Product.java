package com.educandoweb.projeto_spring.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private String nome;
	private String description;
	private Double price;
	private String imgUrl;

	
	@ManyToMany
	// SET - Um conjunto para não ter repetidos
	/*@Transien	→ Essa anotação permite o JPA não considerar qnd for rodar, caso quisesse
	 * 
	 * @JoinTable(...) → Este atributo, por ser muito para muitos, é criada uma tabela que irá ter a PK do Product
	 * e a PK da Category. O 'name' indica o nome dessa nova tabela, o joinColumns indica qual será o nome da PK
	 * referênte a essa entidade, no caso Product. 
	 * inverseJoinColumns→ Porém tbm é necessário informar o nome da PK da entidade da Category aqui mesmo (vc pode escolher aonde quer
	 * fazer esse relacionamento, aqui na Entidade Product ou na Entidade Category, uma das duas. No caso escolhi aqui).
	 * 
	 * Lá na outra entidade, Category, é necessário colocar O @ManyToMany(mappedBy = <nome dessa coleção>), no caso do 
	 * nome da coleção é a 'categories'.
	 * 
	 * */
	@JoinTable(name = "tb_product_category", //indica o nome da nova tabela
	joinColumns = @JoinColumn(name = "product_id"),			//indica o nome da PK dessa entidade, Product
	inverseJoinColumns = @JoinColumn(name = "category_id") )  //indica o nome da PK da outra entidade, Category
	private Set<Category> categories = new HashSet<>(); // começar vazia e o HashSet implementa o Set

	//modidicar/não criar os getters e setters deste atributo
	@Getter(AccessLevel.NONE) 
	@Setter(AccessLevel.NONE)
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();
	
	
	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();
		for(OrderItem x : items) {
			set.add(x.getOrder());
		}
		return set;
	}
	
	public Product() {
	}

	public Product(Long id, String nome, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.nome = nome;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

}
