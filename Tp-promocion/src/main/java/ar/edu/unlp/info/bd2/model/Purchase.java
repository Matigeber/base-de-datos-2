package ar.edu.unlp.info.bd2.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

import java.util.Date;


@Document(indexName = "purchase")
public class Purchase {


	
	@Id
	private String id;
	
	private ProductOnSale productOnSale;
	
	private int quantity;
	
	private User client;
	
	private DeliveryMethod deliveryMethod;
	
	private PaymentMethod paymentMethod;
	
	private String address;
	
	private Float coordX;
	
	private Float coordY;
	
	@Field(type = Date)
	private Date dateOfPurchase;
	
	private Float amount;
	
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
		this.dateOfPurchase = dateOfPurchase;
		this.amount = (productOnSale.getPrice() * quantity) + deliveryMethod.getCost();
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
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		dateOfPurchase = dateOfPurchase;
	}

	public String getId() {
		return id;
	}
	
	public Float getAmount() {
		return amount;
	}
	
	
	
	
}
