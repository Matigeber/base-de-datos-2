package ar.edu.unlp.info.bd2.model;

import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PaymentMethod {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy= "paymentMethod", cascade = CascadeType.ALL) /*por defecto Fetch lazy */
	private List<Purchase> purchases = new ArrayList<Purchase>();
	
	public PaymentMethod(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	public void addPurchase (Purchase purchase) {
		this.purchases.add(purchase);
	}

	public long getId() {
		return id;
	}
	
}
