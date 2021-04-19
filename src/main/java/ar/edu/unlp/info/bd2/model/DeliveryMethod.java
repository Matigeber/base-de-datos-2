package ar.edu.unlp.info.bd2.model;

import java.util.Set;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Float cost;
	
	@Column(nullable = false)
	private Float startWeight;
	
	@Column(nullable = false)
	private Float endWeight;
	
	@OneToMany(mappedBy= "deliveryMethod", cascade = CascadeType.ALL ) 
	private Set<Purchase> purchases;
	
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

	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}

	public long getId() {
		return id;
	}
	
}
