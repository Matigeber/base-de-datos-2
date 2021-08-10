package ar.edu.unlp.info.bd2.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product_on_sale")
public class ProductOnSale {

	@Id
	private String id;
	
	private Provider provider;
	
	private Float price;
	
	private Date initialDate;
	
	private Date finalDate;
	
	public ProductOnSale(){}
	public ProductOnSale(Provider provider , Float price, Date initialDate) {
		this.provider = provider;
		this.price = price;
		this.initialDate = initialDate;
		this.finalDate = null;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
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

	public String getId() {
		return id;
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
