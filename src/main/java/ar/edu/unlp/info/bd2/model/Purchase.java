package ar.edu.unlp.info.bd2.model;

import java.util.Set;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
@Table(name="purchase")

public class Purchase {

	@Id
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ProductOnSale productOnSale;
	
	@Column(nullable = false)
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY) /* agregar el nombre del campo */
	@JoinColumn(name = "user_id",nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_method_id",nullable = false)
	private DeliveryMethod deliveryMethod;
	
	@ManyToOne()
	@JoinColumn(name = "payment_method_id",nullable = false)
	private PaymentMethod paymentMethod;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private Float coordX;
	
	@Column(nullable = false)
	private Float coordY;
	
	@Column(nullable = false)
	private Date dateOfOrder;
	
	
}
