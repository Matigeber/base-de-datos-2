package ar.edu.unlp.info.bd2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Document;

@Document (indexName = "provider")
public class Provider {
	
	@Id
	private String id;
	
	private String name;
	
	private long cuit;
	
	
	public Provider() {}

	public Provider(String name, long cuit) {
		
		this.name = name;
		this.cuit = cuit;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) {
		this.cuit = cuit;
	}

	public String getId() {
		return id;
	}

}
