package ar.edu.unlp.info.bd2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Document;

@Document (indexName = "delivery_method")
public class DeliveryMethod {

	@Id
	private String id;
	
	private String name;
	

	private Float cost;
	

	private Float startWeight;
	

	private Float endWeight;
	
	
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

	public String getId() {
		return id;
	}
	
	public boolean checkShipping (Float weight) {
		return (this.getStartWeight() <= weight && this.getEndWeight() >= weight );
	}
}
