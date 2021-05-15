package ar.edu.unlp.info.bd2.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="product")

public class Product {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private Float weight;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private Category category;
	
	@OneToMany(mappedBy= "product", cascade = CascadeType.ALL ) /* o es CascadeType.DETACH */
	private List<ProductOnSale> productsOnSale;
	
	public Product() {}
	
	public Product(Float weight, String name, Category category) {
		this.weight = weight;
		this.name = name;
		this.category = category;
		this.productsOnSale = new ArrayList<ProductOnSale>();
	}
	
	public long getId() {
		return id;
	}

	public Float getWeight() {
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

	public List<ProductOnSale> getProductsOnSale() {
		return productsOnSale;
	}

	public void setProductsOnSale(List<ProductOnSale> productsOnSale) {
		this.productsOnSale = productsOnSale;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	public void addProductOnsale(ProductOnSale ps) {
		this.productsOnSale.add(ps);
	}
	
	public List<ProductOnSale> getProductsOnSaleByProvider(Provider provider){
	    return this.productsOnSale.stream().filter(pos -> pos.getProvider().getId() == provider.getId()).collect(Collectors.toList());
	  }
	
	
}
