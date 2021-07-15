package ar.edu.unlp.info.bd2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;
import static org.springframework.data.elasticsearch.annotations.FieldType.Long;

@Document(indexName = "bd2")
public class Category {


	@Id
	@Field(type = Long)
	private long id;
	
	@Field(type = Text)
	private String name;
	
	
	public Category() {}
	
	public Category(String aName) {
		this.name = aName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public long getId() {
		return id;
	}
	

	
	
}