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
@Table(name="creditCard_payment")

public class CreditCardPayment extends PaymentMethod {


	@Column(nullable = false)
	private String brand;
	
	@Column(nullable = false)
	private Long number;
	
	@Column(nullable = false)
	private Date expiry;
	
	@Column(nullable = false)
	private Integer cvv;
	
	@Column(nullable = false)
	private String owner;
	
	public CreditCardPayment() {
		super();
	}

	public CreditCardPayment(String name,String brand, Long number, Date expiry, Integer cvv, String owner) {
		super(name);
		this.brand = brand;
		this.number = number;
		this.expiry = expiry;
		this.cvv = cvv;
		this.owner = owner;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
