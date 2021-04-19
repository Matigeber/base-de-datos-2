package ar.edu.unlp.info.bd2.model;

import java.util.Set;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
@Table(name="user")

public class User {
	

	@Id
	private long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String fullname;
	
	@Column(nullable = false)
	private Date birth_date;
	
	@OneToMany(mappedBy= "user", cascade = CascadeType.ALL ) /* o es CascadeType.DETACH */
	private Set<Purchase> purchases;

	public User(String email, String password, String fullname, Date birth_date) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.birth_date = birth_date;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
}
