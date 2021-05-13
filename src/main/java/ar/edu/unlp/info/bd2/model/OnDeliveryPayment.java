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
@Table(name="onDelivery_payment")

public class OnDeliveryPayment extends PaymentMethod {


	@Column(nullable = false)
	private Float promisedAmount;
	
	public OnDeliveryPayment() {}
	
	public OnDeliveryPayment(String name, Float promisedAmount) {
		super(name);
		this.promisedAmount = promisedAmount;
	}

	public Float getPromisedAmount() {
		return promisedAmount;
	}

	public void setPromisedAmount(float promisedAmount) {
		this.promisedAmount = promisedAmount;
	}
}
