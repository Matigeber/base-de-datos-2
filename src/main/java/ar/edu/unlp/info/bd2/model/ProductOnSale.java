package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="product_on_sale")

public class ProductOnSale {
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_id",nullable = false)
	private Provider provider;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id",nullable = false)
	private Product product;
	
	@OneToMany(mappedBy= "productOnSale", cascade = CascadeType.ALL ) /* o es CascadeType.DETACH */
	private List<Purchase> purchases = new ArrayList<Purchase>();
	
	@Column(nullable = false)
	private Float price;
	
	@Column(nullable = false)
	private Date initialDate;
	
	@Column
	private Date finalDate;
	
	public ProductOnSale(){}
	public ProductOnSale(Product product,Provider provider , Float price, Date initialDate) {
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

	public Float getPrice() {
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

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public long getId() {
		return id;
	}
	
	public void addPurchase(Purchase purchase) {
		this.purchases.add(purchase);
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	
	
	
}
