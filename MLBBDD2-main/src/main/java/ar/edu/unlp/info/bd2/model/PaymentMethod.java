package ar.edu.unlp.info.bd2.model;

import javax.persistence.Id;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PaymentMethod {


	@Id
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy= "paymentMethod", cascade = CascadeType.ALL )
	private Set<Purchase> purchases;
	
	public PaymentMethod(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
