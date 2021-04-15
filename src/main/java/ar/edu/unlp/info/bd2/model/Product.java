package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Entity
@Table(name="product")

public class Product {
	

	@Id
	private long id;
	
	@Column(nullable = false)
	private float weight;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private Category category;
	
	@OneToMany(mappedBy= "product", cascade = CascadeType.ALL ) /* o es CascadeType.DETACH */
	private Set<ProductOnSale> productsOnSale;
	
	public Product(float weight, String name, Category category) {
		this.weight = weight;
		this.name = name;
		this.category = category;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProductOnSale> getProductsOnSale() {
		return productsOnSale;
	}

	public void setProductsOnSale(Set<ProductOnSale> productsOnSale) {
		this.productsOnSale = productsOnSale;
	}
	
	
}
