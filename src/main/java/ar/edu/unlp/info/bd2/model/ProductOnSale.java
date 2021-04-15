package ar.edu.unlp.info.bd2.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Entity
@Table(name="product_on_sale")

public class ProductOnSale {
	
	

	/* falta implementar esto si el producto ya tiene un precio para el proveedor se actualiza la fecha de fin en un día antes a la initialDate
	 *  y se el crea el nuevo precio */
	
	@Id
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_id",nullable = false)
	private Provider provider;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id",nullable = false)
	private Product product;
	
	@OneToMany(mappedBy= "productOnSale", cascade = CascadeType.ALL ) /* o es CascadeType.DETACH */
	private Set<Purchase> purchases;
	
	@Column(nullable = false)
	private float price;
	
	@Column(nullable = false)
	private Date initialDate;
	
	public ProductOnSale(Provider provider, Product product, float price, Date initialDate) {
		this.provider = provider;
		this.product = product;
		this.price = price;
		this.initialDate = initialDate;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	
	
	
	
}
