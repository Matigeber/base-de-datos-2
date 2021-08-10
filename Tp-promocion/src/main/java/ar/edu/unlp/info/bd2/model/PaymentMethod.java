package ar.edu.unlp.info.bd2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document (indexName= "payment_method")
public abstract class PaymentMethod {
	
	@Id
	private String id;
	
	private String name;
	
	
	public PaymentMethod() {
		
	}
	public PaymentMethod(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

}
