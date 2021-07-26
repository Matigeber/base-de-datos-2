package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product")
public class Product {
	
	@Id
	private long id;
	

	private Float weight;
	
	private String name;
	

	private Category category;
	
	@Field (type = FieldType.Nested)
	private List<ProductOnSale> productsOnSale;
	
	public Product() {}
	
	public Product(Float weight, String name, Category category) {
		this.weight = weight;
		this.name = name;
		this.category = category;
		this.productsOnSale = new ArrayList<ProductOnSale>();
	}
	
	public long getId() {
		return id;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductOnSale> getProductsOnSale() {
		return productsOnSale;
	}

	public void setProductsOnSale(List<ProductOnSale> productsOnSale) {
		this.productsOnSale = productsOnSale;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	public void addProductOnsale(ProductOnSale ps) {
		this.productsOnSale.add(ps);
	}

}
