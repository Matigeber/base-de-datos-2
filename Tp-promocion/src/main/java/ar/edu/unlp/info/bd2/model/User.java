package ar.edu.unlp.info.bd2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

import static org.springframework.data.elasticsearch.annotations.FieldType.Date;;

@Document (indexName = "user")
public class User {
	
	@Id
	private String id;
	
	private String email;
	
	private String password;
	
	private String fullname;
	
	@Field(type = Date)
	private Date dayOfBirth;
	
public User() {}

	
	public User(String email, String password, String fullname, Date birth_date) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.dayOfBirth = birth_date;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setBirth_date(Date birth_date) {
		this.dayOfBirth = birth_date;
	}


	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

}
