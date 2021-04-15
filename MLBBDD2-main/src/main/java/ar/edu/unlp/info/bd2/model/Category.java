package ar.edu.unlp.info.bd2.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;


@Entity
@Table(name="category")

public class Category {


	@Id
	private long id;
	@Column(nullable = false, unique = true)
	private String name;
	@OneToMany(mappedBy= "category", cascade = CascadeType.ALL ) /* o es CascadeType.DETACH */
	private Set<Product> products;
	
	
	/* aca va la relacion a producto? o se define en el producto */
	
	public Category(String aName) {
		this.name = aName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public long getId() {
		return id;
	}
	
	
}
