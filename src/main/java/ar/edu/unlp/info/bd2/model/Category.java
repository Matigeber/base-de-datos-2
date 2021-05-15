package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;


@Entity
@Table(name="category")

public class Category {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false, unique = true)
	private String name;
	//@OneToMany(mappedBy= "category", cascade = CascadeType.ALL ) /* o es CascadeType.DETACH */
	//private List<Product> products = new ArrayList<Product>();
	
	
	
	public Category() {}
	
	public Category(String aName) {
		this.name = aName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public List<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}*/

	public long getId() {
		return id;
	}
	
	/*public void addProduct (Product product) {
		this.products.add(product);
	}*/


	
	
}
