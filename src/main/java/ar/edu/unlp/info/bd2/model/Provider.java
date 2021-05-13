package ar.edu.unlp.info.bd2.model;

import java.util.Set;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
@Table(name="provider")

public class Provider {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private long cuit;
	
	@OneToMany(mappedBy= "provider", cascade = CascadeType.ALL ) /* o es CascadeType.DETACH */
	private List<ProductOnSale> productsOnSale = new ArrayList<ProductOnSale>();
	
	public Provider() {}

	public Provider(String name, long cuit) {
		
		this.name = name;
		this.cuit = cuit;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) {
		this.cuit = cuit;
	}

	public long getId() {
		return id;
	}

	public List<ProductOnSale> getProductsOnSale() {
		return productsOnSale;
	}

	public void setProductsOnSale(List<ProductOnSale> productsOnSale) {
		this.productsOnSale = productsOnSale;
	}
	
	public void addProductOnSale (ProductOnSale ps) {
		this.productsOnSale.add(ps);
	}
	
	
}
