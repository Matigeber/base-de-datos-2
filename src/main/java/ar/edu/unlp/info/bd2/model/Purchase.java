package ar.edu.unlp.info.bd2.model;

import java.util.Set;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ProductOnSale productOnSale;
	
	@Column(nullable = false)
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY) /* agregar el nombre del campo */
	@JoinColumn(name = "user_id",nullable = false)
	private User client;
	
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
	private Date DateOfPurchase;
	
	public Purchase() {}
	
	public Purchase(ProductOnSale productOnSale, int quantity, User client, DeliveryMethod deliveryMethod,
			PaymentMethod paymentMethod, String address, Float coordX, Float coordY, Date dateOfPurchase) {
		super();
		this.productOnSale = productOnSale;
		this.quantity = quantity;
		this.client = client;
		this.deliveryMethod = deliveryMethod;
		this.paymentMethod = paymentMethod;
		this.address = address;
		this.coordX = coordX;
		this.coordY = coordY;
		DateOfPurchase = dateOfPurchase;
	}

	public ProductOnSale getProductOnSale() {
		return productOnSale;
	}

	public void setProductOnSale(ProductOnSale productOnSale) {
		this.productOnSale = productOnSale;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getCoordX() {
		return coordX;
	}

	public void setCoordX(Float coordX) {
		this.coordX = coordX;
	}

	public Float getCoordY() {
		return coordY;
	}

	public void setCoordY(Float coordY) {
		this.coordY = coordY;
	}

	public Date getDateOfPurchase() {
		return DateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		DateOfPurchase = dateOfPurchase;
	}

	public long getId() {
		return id;
	}
	
	public Float getAmount() {
		return (this.getProductOnSale().getPrice() * this.getQuantity()) + this.deliveryMethod.getCost();
	}
	
	
	
	
}
