package ar.edu.unlp.info.bd2.model;

import java.util.Date;
import org.springframework.data.elasticsearch.annotations.Document;

@Document (indexName = "ondelivery_payment")
public class OnDeliveryPayment extends PaymentMethod{

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
