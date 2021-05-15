package ar.edu.unlp.info.bd2.model;

import java.util.Set;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
@Table(name="delivery_method")

public class DeliveryMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Float cost;
	
	@Column(nullable = false)
	private Float startWeight;
	
	@Column(nullable = false)
	private Float endWeight;
	
	/*@OneToMany(mappedBy= "deliveryMethod", cascade = CascadeType.ALL ) 
	private List<Purchase> purchases = new ArrayList<Purchase>();*/
	
	public DeliveryMethod() {}
	
	public DeliveryMethod(String name, Float cost, Float startWeight, Float endWeight) {
		this.name = name;
		this.cost = cost;
		this.startWeight = startWeight;
		this.endWeight = endWeight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Float getStartWeight() {
		return startWeight;
	}

	public void setStartWeight(Float startWeight) {
		this.startWeight = startWeight;
	}

	public Float getEndWeight() {
		return endWeight;
	}

	public void setEndWeight(Float endWeight) {
		this.endWeight = endWeight;
	}

	public long getId() {
		return id;
	}
	
	public boolean checkShipping (Float weight) {
		return (this.getStartWeight() <= weight && this.getEndWeight() >= weight );
	}

	/*public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	public void addPurchase (Purchase purchase) {
		this.purchases.add(purchase);
	}*/
	
}
